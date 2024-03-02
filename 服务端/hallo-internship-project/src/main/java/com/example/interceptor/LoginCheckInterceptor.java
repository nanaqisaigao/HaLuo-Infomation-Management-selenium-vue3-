package com.example.interceptor;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import com.example.utils.ThreadLocalUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String url = request.getRequestURL().toString();
        log.info("url:{}",url);
        String token = request.getHeader("token");

        //校验令牌
        try {
            log.info("jwt令牌为:{}",token);
            Claims claims = JwtUtils.parseJWT(token);
            Integer id = Integer.valueOf(claims.get("id").toString());
            log.info("用户id为:{}",id);
            User user = userService.getById(id);
            ThreadLocalUtils.setCurrentUser(user);
            String phone = claims.get("phone").toString();
            log.info("用户手机号为:{}",phone);
            //放行
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败");
            response.setStatus(401);//没有访问权限
            return false;
        }

    }

}
