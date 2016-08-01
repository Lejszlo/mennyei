package publicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class PublicApplication {

	@EnableConfigurationProperties
	@EnableTransactionManagement
	static class ApplicationConfig {
	}

	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
}