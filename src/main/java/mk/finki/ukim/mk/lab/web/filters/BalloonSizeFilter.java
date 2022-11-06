//package mk.finki.ukim.mk.lab.web.filters;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class BalloonSizeFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String sizeChosen = (String) request.getSession().getAttribute("sizeChosen");
//
//        String path = request.getServletPath();
//
//        if (!"".equals(path) && !"/selectBalloon".equals(path) && sizeChosen == null) {
////            request.getSession().setAttribute("sizeError", true);
////            request.getSession().setAttribute("sizeErrorText", sizeErrorText);
//            response.sendRedirect("/selectBalloon");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
