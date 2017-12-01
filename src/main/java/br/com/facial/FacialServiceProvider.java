package br.com.facial;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Provider
@Component
@Produces(MediaType.APPLICATION_JSON)
public class FacialServiceProvider implements ContextResolver<ObjectMapper> {

	@Inject
	private ObjectMapper objectMapper;

	public FacialServiceProvider() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		objectMapper = new ObjectMapper();

		objectMapper.registerModule(hibernate5Module);
	}

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}

}
