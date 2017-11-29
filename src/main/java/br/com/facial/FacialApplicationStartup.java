package br.com.facial;

import javax.inject.Inject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.facial.person.Person;
import br.com.facial.person.PersonRepo;
import br.com.facial.user.User;
import br.com.facial.user.UserRepo;

@Component
public class FacialApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    
    @Inject
    private UserRepo userRepo;
    
    @Inject
    private PersonRepo personRepo;
    
    private static final String USER_DEFAULT = "user";
    
    private static final String NAME_DEFAULT = "user";
    
    private static final String EMAIL_DEFAULT = "user@user.com";
    
    private static final String PASSWORD_DEFAULT = "user";
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent arg0) {
        
        createUser();
        
        createPerson();
        
    }
    
    private void createPerson() {
        Person person = personRepo.findByName("Joao");
        
        if (person == null) {
            personRepo.save(Person.builder().name("Joao").lastName("da Silva").phone("47 1234-4567").build());            
        }
        
    }
    
    private void createUser() {
        User userEntity = userRepo.findByUsername(USER_DEFAULT);
        
        if (userEntity == null) {
            User user = new User();
            
            user.setUsername(USER_DEFAULT);
            user.setName(NAME_DEFAULT);
            user.setEmail(EMAIL_DEFAULT);
            user.setPassword(new BCryptPasswordEncoder().encode(PASSWORD_DEFAULT));
            
            userRepo.save(user);
        }
    }
    
}
