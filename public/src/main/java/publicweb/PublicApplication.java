package publicweb;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import publicweb.configuration.property.ServerSettings;

@SpringBootApplication
public class PublicApplication {

	@EnableNeo4jRepositories(basePackages = "core.repository")
	@EnableConfigurationProperties
	@EnableTransactionManagement
	static class ApplicationConfig extends Neo4jConfiguration {

		@Autowired
		private ServerSettings serverSettings;

		@Override
		public Neo4jServer neo4jServer() {
			System.setProperty("username", serverSettings.getUsername());
			System.setProperty("password", serverSettings.getPassword());
			return new RemoteServer(
					"http://" + serverSettings.getAddress().getHostAddress() + ":" + serverSettings.getPort());
		}

		@Override
		public SessionFactory getSessionFactory() {
			return new SessionFactory("core.domain.entity");
		}

		@Bean
		@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
		public Session getSession() throws Exception {
			return super.getSession();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
}