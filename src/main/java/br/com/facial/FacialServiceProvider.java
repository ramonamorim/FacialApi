package br.com.facial;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class FacialServiceProvider implements ContextResolver<ObjectMapper> {

	private ObjectMapper objectMapper;

	public FacialServiceProvider() {
        Hibernate4Module hibernate4Module = new Hibernate4Module();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(hibernate4Module);
    }

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}

}
