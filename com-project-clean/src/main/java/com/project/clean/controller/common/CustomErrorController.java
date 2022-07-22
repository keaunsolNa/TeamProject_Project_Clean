package com.project.clean.controller.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping("/error")
    public ModelAndView error(ModelAndView mv,HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
        	mv.addObject("message", 404);
        	mv.setViewName("/common/error");
        	
            return mv;
        }
        mv.addObject("message", 500);
    	mv.setViewName("/common/error");
        return mv;
    }
}