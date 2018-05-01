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
import software2project.services.historyService;


@ActiveProfiles("test1")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class historyServiceTest {

    @Autowired
    private historyService historyService;
    store store1 = new normalStore();
	product product1 = new normalProduct();
	String email1;
	String type1;
	brand brand1 = new brand();
	Float price1;
	Integer quantity1;
	
	store store2 = new onlineStore();
	product product2 = new onlineProduct();
	String email2;
	String type2;
	brand brand2 = new brand();
	Float price2;
	Integer quantity2;

	store store3 = new normalStore();
	product product3 = new normalProduct();
	String email3;
	String type3;
	brand brand3 = new brand();
	Float price3;
	Integer quantity3;
	
	storeProducts sproduct1 = new storeProducts();
	String email4;
	String type4;
	
	storeProducts sproduct2 = new storeProducts();
	String email5;
	String type5;
	
	storeProducts sproduct3 = new storeProducts();
	String email6;
	String type6;
	
    @Before
    public void setUp() {
    	store1.setId(1);
    	product1.setId(1);
    	email1 = "ali@gmail.com";
    	type1 = "addProduct";
    	brand1.setId(1);
    	price1 = (float) 200;
    	quantity1 = 25;
    	
    	store2.setId(4);
    	product2.setId(5);
    	email2 = "ali@gmail.com";
    	type2 = "addProduct";
    	brand2.setId(1);
    	price2 = (float) 250;
    	quantity2 = 20;
    	
    	store3.setId(1);
    	product3.setId(2);
    	email3 = "alii78@gmail.com";
    	type3 = "addProduct";
    	brand3.setId(2);
    	price3 = (float) 210;
    	quantity3 = 15;
    	
    	sproduct1.setProduct(product1);
    	sproduct1.setStore(store1);
    	email4 = "ali@gmail.com";
    	type4 = "addOffer";
    	
    	sproduct2.setProduct(product2);
    	sproduct2.setStore(store2);
    	email5 = "alii789@gmail.com";
    	type5 = "addOffer";
    	
    	sproduct3.setProduct(product3);
    	sproduct3.setStore(store3);
    	email6 = "ali@gmail.com";
    	type6 = "editProduct";
    	
        Mockito.when(historyService.addHistory(product1, store1, email1, type1, brand1, price1, quantity1)).thenReturn(true);
        Mockito.when(historyService.addHistory(product2, store2, email2, type2, brand2, price2, quantity2)).thenReturn(true);
        Mockito.when(historyService.addHistory(product3, store3, email3, type3, brand3, price3, quantity3)).thenReturn(false);
        Mockito.when(historyService.addHistory(sproduct1, email4, type4)).thenReturn(true);
        Mockito.when(historyService.addHistory(sproduct2, email5, type5)).thenReturn(false);
        Mockito.when(historyService.addHistory(sproduct3, email6, type6)).thenReturn(true);
    }
    
    @Test
    public void getHistoryTC1() {
    	boolean expectedOutput = true;
        boolean actualOutput = historyService.addHistory(product1, store1, email1, type1, brand1, price1, quantity1);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistoryTC2() {
    	boolean expectedOutput = true;
        boolean actualOutput = historyService.addHistory(product2, store2, email2, type2, brand2, price2, quantity2);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistoryTC3() {
    	boolean expectedOutput = false;
        boolean actualOutput = historyService.addHistory(product3, store3, email3, type3, brand3, price3, quantity3);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistory2TC1() {
    	boolean expectedOutput = true;
        boolean actualOutput = historyService.addHistory(sproduct1, email4, type4);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistory2TC2() {
    	boolean expectedOutput = false;
        boolean actualOutput = historyService.addHistory(sproduct2, email5, type5);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistory2TC3() {
    	boolean expectedOutput = true;
        boolean actualOutput = historyService.addHistory(sproduct3, email6, type6);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
  
}