package com.example.springbootcrud.interceptor;

import com.example.springbootcrud.pojo.MsgInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class loginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        MsgInfo msg = (MsgInfo) session.getAttribute("msg");
        String uri = request.getRequestURI();
        log.info("拦截的请求是={}",uri);
        if (userInfo!=null&&msg.getExist()==1){
            log.info("userInfo={}",userInfo);
            return true;
        }else {
            request.getRequestDispatcher("/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
