package br.com.facial.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;

@Component
@Path("users")
public class UserService extends AbstractService<User, UserRepo> {

	@Inject
	private UserRepo userRepo;

	@Override
	protected UserRepo getRepo() {
		return this.userRepo;
	}

	@GET
	@Path("/session")
	@Produces(MediaType.TEXT_PLAIN)
	public Response checkSession(@Context HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Boolean validSession = session != null;
		return Response.ok(validSession, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/signOut")
	@Produces(MediaType.TEXT_PLAIN)
	public Response signOut(@Context HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

}
