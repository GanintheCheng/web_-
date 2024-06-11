//package Filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class GlobalExceptionHandlerFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            // 继续执行请求链
//            chain.doFilter(request, response);
//        } catch (Throwable throwable) {
//            // 异常处理
//            System.out.println(throwable);
//            request.setAttribute("errorMessage", throwable.getMessage());
//            request.getRequestDispatcher("/error.jsp").forward(request, response);
//        }
//    }
//}
