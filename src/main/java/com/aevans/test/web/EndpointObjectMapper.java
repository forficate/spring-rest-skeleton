package com.aevans.test.web;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.module.SimpleModule;

public class EndpointObjectMapper extends ObjectMapper {

	public EndpointObjectMapper() {
		SimpleModule simpleModule = new SimpleModule("SimpleModule", 
	            new Version(1,0,0,null));
		
		simpleModule.addSerializer(EndpointResponse.class, new EndpointResponseSerializer());
		registerModule(simpleModule);
		configure(Feature.INDENT_OUTPUT, true);
	}
}
