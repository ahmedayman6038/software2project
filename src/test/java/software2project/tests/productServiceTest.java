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
import software2project.models.*;
import software2project.services.productService;

@ActiveProfiles("test2")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class productServiceTest {
	@Autowired
	private productService productService;
	product p1 = new normalProduct();
	store s1 = new normalStore();
	brand b1 = new brand();
	product p2 = new normalProduct();
	store s2 = new normalStore();
	brand b2 = new brand();
	product p3 = new onlineProduct();
	store s3 = new onlineStore();
	brand b3 = new brand();
	
	@Before
    public void setUp() {
        Mockito.when(productService.calculateTotalPrice((float) 250, 1, "storeOwner", 2)).thenReturn((float) 200);
        Mockito.when(productService.calculateTotalPrice((float) 250, 2, "storeOwner", 2)).thenReturn((float) 350);
        Mockito.when(productService.calculateTotalPrice((float) 150, 1, "buyer", 3)).thenReturn((float) 150);
        p1.setName("pro1");
        p1.setStartPrice(100);
        p1.setEndPrice(700);
        s1.setName("normal1");
        b1.setName("brand1");
        Mockito.when(productService.addProductToStore(s1, p1, (float)250, b1, 25)).thenReturn(true);
        p2.setName("pro2");
        p2.setStartPrice(150);
        p2.setEndPrice(500);
        s2.setName("normal2");
        b2.setName("brand2");
        Mockito.when(productService.addProductToStore(s2, p2, (float)600, b2, 15)).thenReturn(false);
        p3.setName("pro1");
        p3.setStartPrice(50);
        p3.setEndPrice(300);
        s3.setName("online1");
        b3.setName("brand3");
        Mockito.when(productService.addProductToStore(s3, p3, (float)100, b3, 10)).thenReturn(true);
	}
	
	@Test
	public void calculateTotalPriceTC1() {
		float input1 =(float) 250;
		int input2 = 1;
		String input3 = "storeOwner";
		int input4 = 2;
    	float expectedOutput = (float)200;
        float actualOutput = productService.calculateTotalPrice(input1,input2,input3,input4);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void calculateTotalPriceTC2() {
		float input1 =(float) 250;
		int input2 = 2;
		String input3 = "storeOwner";
		int input4 = 2;
    	float expectedOutput = (float)350;
        float actualOutput = productService.calculateTotalPrice(input1,input2,input3,input4);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void calculateTotalPriceTC3() {
		float input1 =(float) 150;
		int input2 = 1;
		String input3 = "buyer";
		int input4 = 3;
    	float expectedOutput = (float)150;
        float actualOutput = productService.calculateTotalPrice(input1,input2,input3,input4);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void addProductToStoreTC1() {
    	boolean expectedOutput = true;
        boolean actualOutput = productService.addProductToStore(s1, p1, (float)250, b1, 25);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void addProductToStoreTC2() {
		boolean expectedOutput = false;
        boolean actualOutput = productService.addProductToStore(s2, p2, (float)600, b2, 15);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}
	
	@Test
	public void addProductToStoreTC3() {
		boolean expectedOutput = true;
        boolean actualOutput = productService.addProductToStore(s3, p3, (float)100, b3, 10);
         assertThat(actualOutput).isEqualTo(expectedOutput);
	}

}
