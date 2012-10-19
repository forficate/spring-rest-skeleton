package com.aevans.test.web;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

@SuppressWarnings("rawtypes")
public class EndpointResponseSerializer extends JsonSerializer<EndpointResponse>{
	
	

	@Override
	public void serialize(EndpointResponse value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		
		jgen.writeStartObject();
		
		jgen.writeObjectField("result", value.getResult());
		jgen.writeBooleanField("success", value.isSuccess());
		
		if(null != value.getException())
			jgen.writeStringField("exception", value.getException());
		
		if(null != value.getMsg())
			jgen.writeStringField("msg", value.getMsg());
		
		if(null != value.getStackTrace())
			jgen.writeStringField("stacktrace", value.getStackTrace());
		
		
		jgen.writeEndObject();
	}

}
