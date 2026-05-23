package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

// 这个类是自己定义的，不会交给IOC容器管理，所以不能使用@Autowired注入StringRedisTemplate
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private   StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //1.获取请求头中的token
        String token = request.getHeader("authorization");//由前端代码得知，token的key是authorization
        if(StrUtil.isBlank(token)){ //校验token是否为空，为空则放行
            return true;
        }
        //2.根据token获取用户信息
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);//entries()方法获取map中的所有数据

        //3.判断用户信息是否存在
        if(userMap.isEmpty()){ //不存在，放行
            return true;
        }

        //4.存在，将用户信息转换为UserDTO对象
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);//将map中的数据填充到UserDTO对象中,false表示不忽略异常

        //5.保存用户信息到ThreadLocal，供同一线程使用
        UserHolder.saveUser(userDTO);

        //6.刷新token的过期时间
        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        //7.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //1.清除ThreadLocal中的用户信息,防止内存泄漏
        UserHolder.removeUser();
    }

}
