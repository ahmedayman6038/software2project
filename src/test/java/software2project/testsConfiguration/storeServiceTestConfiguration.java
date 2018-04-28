package software2project.testsConfiguration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software2project.services.storeService;

@Profile("test4")
@Configuration
public class storeServiceTestConfiguration {
	 @Bean
	 @Primary
	 public storeService storeService() {
	      return Mockito.mock(storeService.class);
	  }
}
