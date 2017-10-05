package br.com.facial.persistence;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public abstract class AbstractService<T extends BaseEntity, R extends BaseRepo<T>> {

	protected abstract R getRepo();

	@GET
	public List<T> findAll() {
		return this.getRepo().findAll();
	}

	@GET
	@Path("{id}")
	public T find(@PathParam("id") UUID id) {
		return this.getRepo().findOne(id);
	}

	@POST
	@Transactional
	public T save(T entity) {
		return this.getRepo().saveAndFlush(entity);
	}

	@PUT
	@Path("{id}")
	@Transactional
	public T update(@PathParam("id") UUID id, T entity) {
		entity.setId(id);
		return this.getRepo().saveAndFlush(entity);
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void delete(@PathParam("id") UUID id) {
		this.getRepo().delete(id);
	}

}
