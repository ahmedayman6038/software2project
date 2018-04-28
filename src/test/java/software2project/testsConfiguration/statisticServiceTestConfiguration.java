package software2project.testsConfiguration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software2project.services.statisticService;;

@Profile("test3")
@Configuration
public class statisticServiceTestConfiguration {
	 @Bean
	 @Primary
	 public statisticService statisticService() {
	      return Mockito.mock(statisticService.class);
	  }
}
