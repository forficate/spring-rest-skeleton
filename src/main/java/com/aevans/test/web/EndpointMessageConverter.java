package com.aevans.test.web;

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

public class EndpointMessageConverter extends MappingJacksonHttpMessageConverter{
	
	
	public EndpointMessageConverter() {
		super();
		this.setObjectMapper(new EndpointObjectMapper());
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		EndpointResponse<Object> resp = new EndpointResponse<>();
		resp.setResult(object);
		resp.setSuccess(true);
		
		JsonGenerator jsonGenerator =
				getObjectMapper().getJsonFactory().createJsonGenerator(outputMessage.getBody(),  JsonEncoding.UTF8);
		
		getObjectMapper().writerWithDefaultPrettyPrinter().writeValue(jsonGenerator, resp);
		
	}

	
}
