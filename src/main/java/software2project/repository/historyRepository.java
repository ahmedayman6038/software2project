package software2project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import software2project.models.history;


public interface historyRepository extends CrudRepository<history, Integer>{
	  @Query("select s from history s where s.storeId = ?1 ORDER BY s.id DESC")
	  List<history> getStoreHistory(Integer sid);
	  
	  @Transactional
	  @Modifying
	  @Query("delete history s where s.id = ?1")
	  void deleteHistory(Integer hid);
	  
	  @Query("select s from history s where s.id = ?1")
	  history getHistory(Integer hid);
}
