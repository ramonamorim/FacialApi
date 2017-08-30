package br.com.facial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FacialApplicationConfig.class)
public class FacialApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacialApplication.class, args);
	}
}
