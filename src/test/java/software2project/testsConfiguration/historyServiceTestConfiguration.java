package software2project.testsConfiguration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software2project.services.historyService;

@Profile("test1")
@Configuration
public class historyServiceTestConfiguration {
	 @Bean
	 @Primary
	 public historyService historyService() {
	      return Mockito.mock(historyService.class);
	  }
}
