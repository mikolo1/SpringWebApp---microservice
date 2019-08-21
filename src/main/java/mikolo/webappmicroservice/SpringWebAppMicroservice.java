package mikolo.webappmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "mikolo.webappmicroservice.repository")
@SpringBootApplication
public class SpringWebAppMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebAppMicroservice.class, args);
	}

}
