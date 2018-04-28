package software2project.testsConfiguration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import software2project.services.productService;

@Profile("test2")
@Configuration
public class productServiceTestConfiguration {
	 @Bean
	 @Primary
	 public productService productService() {
	      return Mockito.mock(productService.class);
	  }
}
