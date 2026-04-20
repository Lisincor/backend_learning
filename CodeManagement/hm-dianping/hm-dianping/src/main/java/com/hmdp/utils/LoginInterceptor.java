package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

// 这个类是自己定义的，不会交给IOC容器管理，所以不能使用@Autowired注入StringRedisTemplate
public class LoginInterceptor implements HandlerInterceptor {

    private   StringRedisTemplate stringRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取session
//        HttpSession session = request.getSession();
//        //2.获取session中的用户信息
//        Object user = session.getAttribute("user");
//        //3.判断用户信息是否存在
//        if(user == null) {
//            //4.如果不存在，返回错误信息
//            response.setStatus(401);
//            return false;
//        }
//
//        //4.存在，取的对象是UserDTO保存用户信息到ThreadLocal
//        UserHolder.saveUser((UserDTO) user);
//
//        //5.放行
//        return true;

//
//        //1.获取请求头中的token
//        String token = request.getHeader("authorization");//由前端代码得知，token的key是authorization
//        if(StrUtil.isBlank(token)){ //校验token是否为空，为空则返回错误信息
//            //如果token不存在，返回错误信息
//            response.setStatus(401);
//            return false;
//        }
//        //2.根据token获取用户信息
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(LOGIN_USER_KEY + token);
//
//        //3.判断用户信息是否存在
//        if(userMap.isEmpty()){
//            //如果用户信息不存在，返回错误信息
//            response.setStatus(401);
//            return false;
//        }
//
//        //4.存在，将用户信息转换为UserDTO对象
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);//将map中的数据填充到UserDTO对象中,false表示不忽略异常
//
//        //5.保存用户信息到ThreadLocal
//        UserHolder.saveUser(userDTO);
//
//        //6.刷新token的过期时间
//        stringRedisTemplate.expire(LOGIN_USER_KEY + token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        //7.放行
        if(UserHolder.getUser() == null){
            //如果用户信息不存在，返回错误信息
            response.setStatus(401);
            //拦截
            return false;
        }
        //有用户则方行
        return true;
    }


}
