package br.com.facial.person;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import br.com.facial.persistence.AbstractService;

@Component
@Path("persons")
public class PersonService extends AbstractService<Person, PersonRepo> {
    
    @Inject
    private PersonRepo personRepo;
    
    @Override
    protected PersonRepo getRepo() {
        return this.personRepo;
    }
    
}
