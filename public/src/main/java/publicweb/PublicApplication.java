package publicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PublicApplication {

	@EnableConfigurationProperties
	static class ApplicationConfig {
	}

	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
}