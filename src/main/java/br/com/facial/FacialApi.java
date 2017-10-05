package br.com.facial;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class FacialApi extends Application {

	public FacialApi() {
	}

}
