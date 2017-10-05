package br.com.facial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.facial.user.User;
import br.com.facial.user.UserRepo;

@Service
public class CustomDetailsService implements UserDetailsService {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7124868596374983056L;

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found!", username));
		}
		return new UserRepoDetails(user);
	}

}
