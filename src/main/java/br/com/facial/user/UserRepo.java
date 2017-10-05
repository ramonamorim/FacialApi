package br.com.facial.user;

import java.util.List;

import br.com.facial.persistence.BaseRepo;

public interface UserRepo extends BaseRepo<User> {

	List<User> findUserById(String id);

	User findByUsername(String username);

}
