package com.nj.common.interceptor;

import com.nj.common.annotations.RoleAllowed;
import com.nj.common.enums.RoleType;
import com.nj.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
public class RoleValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
//        if (uri.contains("/auth") || uri.contains("/index")){
//            return true;
//        }

        if (!(handler instanceof HandlerMethod method)) {
            return true;
        }

        RoleAllowed roleAllowed = findRoleAllowedAnnotation(method);
        if (roleAllowed == null) {
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        User user = (User) session.getAttribute("currUser");
        RoleType userRole = user.getRole();

        for (RoleType allowed : roleAllowed.value()) {
            if (allowed == userRole) {
                return true;
            }
        }

        response.sendRedirect(request.getContextPath() + "/auth/access-denied");
        return false;
    }

    private boolean isRoleAllowed(RoleType userRole, RoleAllowed roleAllowed) {
        for (RoleType allowed : roleAllowed.value()) {
            if (allowed == userRole) {
                return true;
            }
        }
        return false;
    }

    private RoleAllowed findRoleAllowedAnnotation(HandlerMethod method) {
        RoleAllowed roleAllowed = method.getMethodAnnotation(RoleAllowed.class);
        if (roleAllowed != null) {
            return roleAllowed;
        }
        return method.getBeanType().getAnnotation(RoleAllowed.class);
    }
}
