package software2project.tests;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import software2project.Main;
import software2project.services.statisticService;


@ActiveProfiles("test3")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class statisticServiceTest {
	
	@Autowired
	private statisticService statisticService;
	
	@Before
    public void setUp() {
        Mockito.when(statisticService.getViewedProduct(1)).thenReturn(22);
        Mockito.when(statisticService.getViewedProduct(5)).thenReturn(0);
        Mockito.when(statisticService.getViewedProduct(3)).thenReturn(4);    
        Mockito.when(statisticService.getProductsInStore(1)).thenReturn(2);
        Mockito.when(statisticService.getProductsInStore(5)).thenReturn(0);
        Mockito.when(statisticService.getProductsInStore(3)).thenReturn(2);
        Mockito.when(statisticService.getBuyedProduct(1)).thenReturn(3);
        Mockito.when(statisticService.getBuyedProduct(5)).thenReturn(0);
        Mockito.when(statisticService.getBuyedProduct(3)).thenReturn(1);
        List<String> products1 = new ArrayList<String>();
        products1.add("wwe");
        List<String> products2 = new ArrayList<String>();
        products2.add("pro1");
        List<String> products3 = new ArrayList<String>();
        Mockito.when(statisticService.getSoldOutProduct(1)).thenReturn(products1);
        Mockito.when(statisticService.getSoldOutProduct(5)).thenReturn(products3);
        Mockito.when(statisticService.getSoldOutProduct(3)).thenReturn(products2);
	}
	
	@Test
	public void getViewedProductTC1() {
		int input = 1;
    	int expectedOutput = 22;
    	int actualOutput = statisticService.getViewedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getViewedProductTC2() {
		int input = 5;
    	int expectedOutput = 0;
    	int actualOutput = statisticService.getViewedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getViewedProductTC3() {
		int input = 3;
    	int expectedOutput = 4;
    	int actualOutput = statisticService.getViewedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getProductsInStoreTC1() {
		int input = 1;
    	int expectedOutput = 2;
    	int actualOutput = statisticService.getProductsInStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getProductsInStoreTC2() {
		int input = 5;
    	int expectedOutput = 0;
    	int actualOutput = statisticService.getProductsInStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getProductsInStoreTC3() {
		int input = 3;
    	int expectedOutput = 2;
    	int actualOutput = statisticService.getProductsInStore(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getBuyedProductTC1() {
		int input = 1;
    	int expectedOutput = 3;
    	int actualOutput = statisticService.getBuyedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getBuyedProductTC2() {
		int input = 5;
    	int expectedOutput = 0;
    	int actualOutput = statisticService.getBuyedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getBuyedProductTC3() {
		int input = 3;
    	int expectedOutput = 1;
    	int actualOutput = statisticService.getBuyedProduct(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getSoldOutProductTC1() {
		int input = 1;
    	int expectedOutput = 1;
    	List<String> actualOutput = statisticService.getSoldOutProduct(input);
         assertThat(actualOutput.size()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getSoldOutProductTC2() {
		int input = 3;
    	int expectedOutput = 1;
    	List<String> actualOutput = statisticService.getSoldOutProduct(input);
         assertThat(actualOutput.size()).isEqualTo(expectedOutput);
	}
	
	@Test
	public void getSoldOutProductTC3() {
		int input = 5;
    	int expectedOutput = 0;
    	List<String> actualOutput = statisticService.getSoldOutProduct(input);
         assertThat(actualOutput.size()).isEqualTo(expectedOutput);
	}

}
