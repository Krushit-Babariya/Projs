//package com.nj.common.filter;
//
//import com.nj.common.annotations.RoleAllowed;
//import com.nj.common.enums.RoleType;
//import com.nj.common.exception.AuthException;
//import com.nj.entity.User;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.method.HandlerMethod;
//
//import java.io.IOException;
//
//@Component
//public class RoleValidationFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        Object handler = request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler");
//
//        if (!(handler instanceof HandlerMethod method)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        RoleAllowed roleAllowed = findRoleAllowedAnnotation(method);
//        System.out.println("Role Allowed :: " + roleAllowed);
//        if (roleAllowed == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        HttpSession session = request.getSession();
//
//        try {
//            User user = (User) session.getAttribute("currUser");
//
//            if (user == null || user.getRole() == null) {
//                throw new AuthException("");
//            } 
//
//            RoleType userRole = user.getRole();
//            if (isRoleAllowed(userRole, roleAllowed)) {
//                request.setAttribute("user", user);
//                filterChain.doFilter(request, response);
//            } else {
//                throw new AuthException("");
//            }
//        } catch (AuthException ex) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//        }
//    }
//
//    private boolean isRoleAllowed(RoleType userRole, RoleAllowed roleAllowed) {
//        for (RoleType allowed : roleAllowed.value()) {
//            if (allowed == userRole) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private RoleAllowed findRoleAllowedAnnotation(HandlerMethod method) {
//        RoleAllowed roleAllowed = method.getMethodAnnotation(RoleAllowed.class);
//        if (roleAllowed != null) {
//            return roleAllowed;
//        }
//        return method.getBeanType().getAnnotation(RoleAllowed.class);
//    }
//}
