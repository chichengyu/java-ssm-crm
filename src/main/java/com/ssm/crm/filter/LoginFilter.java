package com.ssm.crm.filter;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.ssm.crm.constart.UserConstart;
import com.ssm.crm.pojo.User;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/** 该项目用拦截器做登陆，没用过滤器 ，因为拦截器是spring框架的，而过滤器是java的
 * 登陆过滤器
 */
public class LoginFilter implements Filter {
    // 定义全局需要过滤的 url
    private static Set<String> exclusionUrlSet = Sets.newConcurrentHashSet();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        List<String> exclusionUrlList = Splitter.on(",").omitEmptyStrings().splitToList(exclusionUrls);
        exclusionUrlSet = Sets.newConcurrentHashSet(exclusionUrlList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 开放路由
        // System.out.println(request.getContextPath());// 获取的是 /crm
        String servletPath = request.getServletPath();// 获取的是访问路径 /customer/list
        if (exclusionUrlSet.contains(servletPath)){
            filterChain.doFilter(request,response);
            return;
        }
        User user = (User)request.getSession().getAttribute(UserConstart.USER_KEY);
        // 未登录无权限
        if (ObjectUtils.isEmpty(user)){
            request.getRequestDispatcher("/dologin").forward(request,response);
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
