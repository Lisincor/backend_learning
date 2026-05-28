# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

hm-dianping（黑马点评）是一个仿大众点评/美团的本地生活服务应用，基于 Spring Boot 2.7.18 + Java 17 构建。

## 构建与运行

```bash
# 构建
cd hm-dianping
mvn clean package

# 运行（端口 8081）
mvn spring-boot:run

# 运行单个测试
mvn test -Dtest=HmDianPingApplicationTests
```

**前置依赖：** MySQL（`127.0.0.1:3306/hmdp`）和 Redis（`192.168.88.128:6379`）需提前启动。数据库初始化脚本位于 `src/main/resources/db/hmdp.sql`。

## 架构

标准分层 Spring Boot MVC（Controller → Service → Mapper）：

- **controller/** — REST 接口，统一返回 `Result` DTO
- **service/impl/** — 业务逻辑，继承 MyBatis-Plus `ServiceImpl`
- **mapper/** — MyBatis-Plus `BaseMapper`，仅 `VoucherMapper.xml` 有自定义 SQL
- **config/** — 拦截器注册、MyBatis 分页、Redisson 客户端、全局异常处理
- **utils/** — Redis 缓存工具、分布式锁、认证拦截器、ID 生成器
- **entity/** — 数据库实体，映射 `tb_*` 表（`@TableName`）
- **dto/** — `Result`（统一响应）、`LoginFormDTO`、`UserDTO`、`ScrollResult`

入口类 `HmDianPingApplication` 使用 `@MapperScan` 和 `@EnableAspectJAutoProxy(exposeProxy = true)`（用于 AOP 自调用事务）。

## 认证机制（双层拦截器）

`MvcConfig` 注册两个拦截器：

1. **RefreshTokenInterceptor**（order=0）— 从 `authorization` 请求头读取 token，在 Redis Hash 中查找用户，存入 `UserHolder`（ThreadLocal），刷新 token TTL。始终放行。
2. **LoginInterceptor**（order=1）— 检查 `UserHolder.getUser()`，为空则返回 401。排除路径：`/user/code`、`/user/login`、`/blog/hot`、`/shop/**`、`/shop-type/**`、`/voucher/**`、`/upload/**`。

## Redis 使用场景

Redis 在本项目中承担多种角色，key 命名规范见 `RedisConstants`：

| 用途 | Key 模式 | 数据结构 |
|---|---|---|
| 登录 token | `login:token:{uuid}` | Hash |
| 验证码 | `login:code:{phone}` | String |
| 店铺缓存 | `cache:shop:{id}` | String |
| 店铺地理坐标 | `shop:geo:{typeId}` | GEO |
| 秒杀库存 | `seckill:stock:{voucherId}` | String |
| 秒杀订单去重 | `seckill:order:{voucherId}` | Set |
| 异步订单队列 | `stream.orders` | Stream |
| 博客点赞 | `blog:liked:{blogId}` | SortedSet |
| 用户 Feed | `feed:{userId}` | SortedSet |
| 签到记录 | `sign:{userId}:{yyyyMM}` | Bitmap |
| 分布式 ID | `icr:*` | String（INCR） |

## 核心业务流程

### 秒杀下单（最复杂的流程）

`VoucherOrderServiceImpl` 实现了完整的秒杀链路：

1. `seckill.lua` 原子操作：校验库存 → 校验用户重复下单 → 扣减库存 → 写入 Redis Stream
2. 后台线程 `VoucherOrderHandler` 消费 Stream，使用 Redisson 分布式锁处理订单
3. 通过 `AopContext.currentProxy()` 获取代理对象调用 `createVoucherOrder()`，确保 `@Transactional` 生效

### 缓存模式

`CacheClient` 和 `ShopServiceImpl` 展示了三种缓存策略：
- **缓存穿透** — 空值缓存（TTL 2 分钟）
- **缓存击穿（互斥锁）** — `tryLock` 重试 + 50ms 间隔
- **缓存击穿（逻辑过期）** — 数据永不过期，后台线程异步刷新

### 分布式锁

两种实现：
- `SimpleRedisLock` — 自定义 SETNX + Lua 解锁（`unlock.lua`）
- Redisson — 用于秒杀订单处理

## 已知问题

- `ILcok.java` 文件名拼写错误（应为 `ILock.java`）
- `SystemConstants.java` 中硬编码了本地文件路径
- `UserController.logout()` 未实现
- `BlogCommentsController` 为空壳
- 测试覆盖仅包含默认的 Spring Boot 测试桩
