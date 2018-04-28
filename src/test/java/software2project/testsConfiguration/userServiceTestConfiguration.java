package software2project.testsConfiguration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software2project.services.userService;

@Profile("test5")
@Configuration
public class userServiceTestConfiguration {
	 @Bean
	 @Primary
	 public userService userService() {
	      return Mockito.mock(userService.class);
	  }
}
