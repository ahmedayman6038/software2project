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
import software2project.services.storeService;

@ActiveProfiles("test4")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class storeServiceTest {
	@Autowired
	private storeService storeService;
	
	@Before
    public void setUp() {
        Mockito.when(storeService.acceptStore(1)).thenReturn(true);
        Mockito.when(storeService.acceptStore(5)).thenReturn(false);
        Mockito.when(storeService.acceptStore(2)).thenReturn(true);
	}
	
	@Test
	public void acceptStoreTC1() {
		int input = 1;
    	boolean expectedOutput = true;
    	boolean actualOutput = storeService.acceptStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void acceptStoreTC2() {
		int input = 5;
    	boolean expectedOutput = false;
    	boolean actualOutput = storeService.acceptStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void acceptStoreTC3() {
		int input = 2;
    	boolean expectedOutput = true;
    	boolean actualOutput = storeService.acceptStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}

}
