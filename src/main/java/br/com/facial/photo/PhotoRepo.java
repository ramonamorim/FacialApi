package br.com.facial.photo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import br.com.facial.persistence.BaseRepo;
import br.com.facial.person.Person;

public interface PhotoRepo extends BaseRepo<Photo> {
    
    @EntityGraph(attributePaths = "person", type = EntityGraphType.LOAD)
    List<Photo> findPhotoByPerson(Person person);
}
