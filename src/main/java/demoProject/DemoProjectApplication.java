package demoProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

	Logger logger = LoggerFactory.getLogger(DemoProjectApplication.class);

//	@Value("${dbpassword:}")
//	String password;
//
//	@Value("${dbusername:}")
//	String username;
//
//	@PostConstruct
//	private void postConstruct() {
//		logger.info(username);
//		logger.info(password);
//	}

}
