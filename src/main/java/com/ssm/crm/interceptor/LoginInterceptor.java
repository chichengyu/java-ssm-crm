package com.ssm.crm.interceptor;

import com.ssm.crm.constart.UserConstart;
import com.ssm.crm.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在Controller处理之前进行调用。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("请求执行前拦截");
        //System.out.println(request.getContextPath());// /crm
        //System.out.println(request.getServletPath());// /dologin
        // 公共路径
        String url = request.getRequestURL().toString();// http://localhost:8080/crm/dologin
        if (url.contains("/dologin") || url.contains("/logout") || url.contains("/captcha")){
            return true;
        }
        User user = (User)request.getSession().getAttribute(UserConstart.USER_KEY);
        if (user != null){
            return true;
        }
        response.sendRedirect("/crm/dologin");
        return false;
    }

    /**
     * 在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操作。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("请求执行完成后拦截");
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行");
    }
}
