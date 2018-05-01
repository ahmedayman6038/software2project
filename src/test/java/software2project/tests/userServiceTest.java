package software2project.tests;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import software2project.Main;
import software2project.models.user;
import software2project.services.userService;

@ActiveProfiles("test5")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class userServiceTest {
	@Autowired
	private userService userService;
	user input1 = new user();
	user input2 = new user();
	user input3 = new user();
	user input4 = new user();
	user input5 = new user();
	user input6 = new user();
	@Before
    public void setUp() {
		input1.setEmail("ahmed@gmail.com");
		input1.setPassword("123");
		
		input2.setEmail("ahmed123@gmail.com");
		input2.setPassword("123");
		
		input3.setEmail("ali@gmail.com");
		input3.setPassword("123");
		
		input4.setName("ahmed123");
		input4.setType("buyer");
		input4.setEmail("ahmed123@gmail.com");
		input4.setPassword("123456");
		
		input5.setName("ahmed456");
		input5.setType("buyer");
		input5.setEmail("ahmed@gmail.com");
		input5.setPassword("123789");
		
		input6.setName("ali123");
		input6.setType("storeOwner");
		input6.setEmail("ali123@gmail.com");
		input6.setPassword("123");
		
		user user = new user();
		user.setName("ahmed");
		user user2 = new user();
		user user3 = new user();
        Mockito.when(userService.login(input1)).thenReturn(user);
        Mockito.when(userService.login(input2)).thenReturn(user2);
        Mockito.when(userService.login(input3)).thenReturn(user3);
        Mockito.when(userService.register(input4)).thenReturn(true);
        Mockito.when(userService.register(input5)).thenReturn(false);
        Mockito.when(userService.register(input6)).thenReturn(true);
	}
	
	@Test
	public void loginTC1() {
    	String expectedOutput = "ahmed";
    	user actualOutput = userService.login(input1);
         assertThat(actualOutput.getName()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void loginTC2() {
    	String expectedOutput = null;
    	user actualOutput = userService.login(input2);
         assertThat(actualOutput.getName()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void loginTC3() {
    	String expectedOutput = null;
    	user actualOutput = userService.login(input2);
         assertThat(actualOutput.getName()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void registerTC1() {
    	boolean expectedOutput = true;
    	boolean actualOutput = userService.register(input4);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void registerTC2() {
    	boolean expectedOutput = false;
    	boolean actualOutput = userService.register(input5);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void registerTC3() {
    	boolean expectedOutput = true;
    	boolean actualOutput = userService.register(input6);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}

}
