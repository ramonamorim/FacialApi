package br.com.facial.person;

import br.com.facial.persistence.BaseRepo;

public interface PersonRepo extends BaseRepo<Person> {

    Person findByName(String name);
}
