package com.springcloud.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 解决webhook自动刷新配置报400问题
 */
@WebFilter(urlPatterns = "/actuator/bus-refresh")
public class WebHookFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new CustometRequestWrapper(servletRequest),servletResponse);
    }

    @Override
    public void destroy() {}

    //使用HttpServletRequest包装原始请求达到修改post请求中body内容的目的
    private static class CustometRequestWrapper extends HttpServletRequestWrapper {
        CustometRequestWrapper(ServletRequest request) {
            super((HttpServletRequest) request);
        }
        @Override
        public ServletInputStream getInputStream() {
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {return true; }
                @Override
                public boolean isReady() {return false;}
                @Override
                public void setReadListener(ReadListener readListener) {}
                @Override
                public int read() {
                    return -1;
                }
            };
        }
    }
}
