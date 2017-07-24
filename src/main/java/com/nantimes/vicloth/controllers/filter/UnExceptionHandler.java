package com.nantimes.vicloth.controllers.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;



public class UnExceptionHandler implements HandlerExceptionResolver {  

	private static Logger logger = Logger.getLogger(UnExceptionHandler.class);  

	//@Autowired
	//QueueService queueService;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
			Exception ex) {

		logger.error(ex.getMessage());
		//queueService.send(request.getRequestURI(), "error", ex.getMessage());

		HandlerMethod mathod = (HandlerMethod) handler;
		ResponseBody body = mathod.getMethodAnnotation(ResponseBody.class);
	
		if (body == null) {       	     
			return new ModelAndView("error/noAccess.do");  
		}
		else {
		
			response.setContentType("text/html;charset=UTF-8"); 
			try {
				response.getWriter().write("{\"statusCode\":300,\"message\":\""+ ex.getMessage() +"\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   		    		
			return new ModelAndView();
		}  	
	}  
}  
