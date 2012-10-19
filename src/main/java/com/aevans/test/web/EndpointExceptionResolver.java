package com.aevans.test.web;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


public class EndpointExceptionResolver implements HandlerExceptionResolver {
	
	private final MappingJacksonJsonView view;
	
	private boolean debug = false;
	
	public EndpointExceptionResolver() {
		view = new MappingJacksonJsonView();
		ObjectMapper om = new EndpointObjectMapper();
		view.setObjectMapper(om);
		view.setExtractValueFromSingleKeyModel(true);
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		

		EndpointResponse<Object> result = new EndpointResponse<>();
		result.setSuccess(false);
		result.setException(ex.getClass().getSimpleName());
		result.setMsg(ex.getMessage());
		
		if(debug) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			result.setStackTrace(sw.toString());
		}

		ModelAndView mv = new ModelAndView(view);
		mv.addObject(result);
		
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		
		return mv;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	

}
