package br.com.facial.usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;


@Component
@Path("usuarios")
public class UsuarioServico {
	

	
	@GET
	@Path("teste")
	@Produces
	public String testeApi(){
		
		return "Hello World";
		
	}
}
