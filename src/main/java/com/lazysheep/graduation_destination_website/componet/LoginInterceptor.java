package com.lazysheep.graduation_destination_website.componet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目标方法执行前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        Object LoginUser = request.getSession().getAttribute("user");
        if(LoginUser == null){
            //未登录
            request.setAttribute("msg","您未登录，没有此权限");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }else{
            //放行
            return true;
        }
    }

    /**
     * 目标方法后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        log.info("postHandle执行{}",modelAndView);
    }


    /**
     * 页面渲染后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
        log.info("afterHandle执行异常{}",ex);
    }
}
