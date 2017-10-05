package br.com.facial.photo;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;

@Component
@Path("photos")
public class PhotoService extends AbstractService<Photo, PhotoRepo> {

	@Inject
	private PhotoRepo photoRepo;

	@Override
	protected PhotoRepo getRepo() {
		return this.photoRepo;
	}
}
