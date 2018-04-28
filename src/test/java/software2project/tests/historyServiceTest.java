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
import software2project.models.history;
import software2project.services.historyService;


@ActiveProfiles("test1")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class historyServiceTest {

    @Autowired
    private historyService historyService;

    @Before
    public void setUp() {
        Mockito.when(historyService.getStoreHistory(1)).thenReturn(2);
        Mockito.when(historyService.getStoreHistory(5)).thenReturn(0);
        Mockito.when(historyService.getStoreHistory(3)).thenReturn(1);
        history his1 = new history();
        his1.setStoreId(1);
        Mockito.when(historyService.getHistory(1)).thenReturn(his1);
        history his2 = new history();
        Mockito.when(historyService.getHistory(5)).thenReturn(his2);
        history his3 = new history();
        his3.setStoreId(3);
        Mockito.when(historyService.getHistory(3)).thenReturn(his3);
    }
    
    @Test
    public void getStoreHistoryTC1() {
    	int input = 1;
    	int expectedOutput = 2;
        int actualOutput = historyService.getStoreHistory(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getStoreHistoryTC2() {
    	int input = 5;
    	int expectedOutput = 0;
        int actualOutput = historyService.getStoreHistory(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getStoreHistoryTC3() {
    	int input = 3;
    	int expectedOutput = 1;
        int actualOutput = historyService.getStoreHistory(input);
         assertThat(actualOutput).isEqualTo(expectedOutput);
    }
    
    @Test
    public void getHistoryTC1() {
    	int input = 1;
    	history expectedOutput = new history();
    	expectedOutput.setStoreId(1);
        history actualOutput = historyService.getHistory(input);
         assertThat(actualOutput.getStoreId()).isEqualTo(expectedOutput.getStoreId());
    }
    
    @Test
    public void getHistoryTC2() {
    	int input = 5;
    	history expectedOutput = new history();
        history actualOutput = historyService.getHistory(input);
         assertThat(actualOutput.getStoreId()).isEqualTo(expectedOutput.getStoreId());
    }
    
    @Test
    public void getHistoryTC3() {
    	int input = 3;
    	history expectedOutput = new history();
    	expectedOutput.setStoreId(3);
        history actualOutput = historyService.getHistory(input);
         assertThat(actualOutput.getStoreId()).isEqualTo(expectedOutput.getStoreId());
    }
}