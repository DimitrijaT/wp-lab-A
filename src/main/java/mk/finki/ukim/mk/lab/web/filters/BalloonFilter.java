//package mk.finki.ukim.mk.lab.web.filters;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class BalloonFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String colorChosen = (String) request.getSession().getAttribute("colorChosen");
//
//        String path = request.getServletPath();
//
//        ServletContext servletContext = request.getServletContext();
//
//        if (servletContext.getAttribute("orderCount") == null) {
//            servletContext.setAttribute("orderCount", 0);
//        }
//
//        if (!path.contains("/balloons") && !"".equals(path) && !"/listBalloons.css".equals(path) && colorChosen == null) {
////            WebContext webContext = new WebContext(request,response, request.getServletContext());
////            webContext.setVariable("colorError",true);
////            webContext.setVariable("colorErrorText","Please enter a value!");
//            response.sendRedirect("/");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
