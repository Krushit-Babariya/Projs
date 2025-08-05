package com.nj.common.exception;

import com.nj.common.messages.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public String handleApplicationException(
            ApplicationException ex,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(DBException.class)
    public String handleDBException(
            DBException ex,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        ex.printStackTrace();
        redirectAttributes.addFlashAttribute("error", "Database error: " + ex.getMessage());
        return "redirect:" + getReferer(request);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception ex,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        ex.printStackTrace();
        redirectAttributes.addFlashAttribute("error", "Unexpected error occurred.");
        return "redirect:" + getReferer(request);
    }

//    private String getReferer(HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
//        if (referer != null && !referer.isBlank()) {
//            String path = referer.replaceFirst("https?://[^/]+", "");
//            if (path.contains(".jsp")) {
//                return "/index";
//            }
//            int queryIndex = path.indexOf("?");
//            if (queryIndex != -1) {
//                path = path.substring(0, queryIndex);
//            }
//            return path;
//        }
//        return "/index";
//    }
//    private String getReferer(HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
//        return (referer != null) ? referer.replaceFirst("http://[^/]+", "") : "/";
//    }

    private String getReferer(HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        if (referer != null) {
            String path = referer.replaceFirst("http://[^/]+", "");
            if (path.contains(".jsp")) {
                return "/";
            }
            return path;
        }
        return "/";
    }
}
