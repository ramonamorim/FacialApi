package br.com.facial.person;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.facial.persistence.AbstractService;

@Component
@Path("persons")
public class PersonService extends AbstractService<Person, PersonRepo> {

	@Inject
	private PersonRepo personRepo;

	@Override
	public Person save(Person entity) {
		return super.save(entity);
	}

	@POST
	@Transactional
	public Person savingLives(Person entity) {
		return this.personRepo.saveAndFlush(entity);
	}

	@Override
	protected PersonRepo getRepo() {
		return this.personRepo;
	}
}
