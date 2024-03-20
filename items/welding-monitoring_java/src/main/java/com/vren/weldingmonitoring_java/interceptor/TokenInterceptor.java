package com.vren.weldingmonitoring_java.interceptor;

import com.vren.weldingmonitoring_java.anno.NoNeedLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTH_PREFIX = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if (!isHandlerMethod) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(NoNeedLogin.class) || method.isAnnotationPresent(NoNeedLogin.class)) {
            return true;
        }
        // 从请求头中获取 Token
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (null == token) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        token = token.replace(AUTH_PREFIX, "");
        // 验证 Token 是否有效，这里可以根据具体的验证逻辑进行实现
        boolean isValidToken = validateToken(token);

        if (!isValidToken) {
            // Token 无效，返回错误响应
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // Token 有效，继续处理请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 拦截器后处理方法，可以在此处进行一些操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后的操作，可以在此处进行一些操作
    }

    @Value("${login.token}")
    private String token;

    private boolean validateToken(String requestToken) {
        // 在这里编写校验Token的逻辑
        // 返回 true 表示 Token 有效，返回 false 表示 Token 无效
        // 实际校验逻辑需要根据具体需求来实现
        // 可以使用 JWT 等相关库来进行 Token 的解析和校验
        // 这里只是示例，假设只要 Token 不为空即为有效
        if (token.equals(requestToken)) {
            return true;
        }
        return false;
    }
}