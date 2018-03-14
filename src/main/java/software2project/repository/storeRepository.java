package software2project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import software2project.models.normalStore;
import software2project.models.onlineStore;
import software2project.models.store;
import software2project.models.storeProducts;


/**
 * This is Store Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface storeRepository extends CrudRepository<store, Integer> {
	  @Query("select s from normalStore s where s.user.id = ?1 and accepted = true")
	  List<normalStore> getNormalStores(Integer userId);
	  
	  @Query("select s from onlineStore s where s.user.id = ?1 and accepted = true")
	  List<onlineStore> getOnlineStores(Integer userId);
	  
	  @Query("select s from normalStore s")
	  List<normalStore> getAllNormalStores();
	  
	  @Query("select s from onlineStore s")
	  List<onlineStore> getAllOnlineStores();
	  
	  @Query("select s from normalStore s where s.id = ?1")
	  normalStore getNormalStore(Integer id);
		  
	  @Query("select s from onlineStore s where s.id = ?1")
	  onlineStore getOnlineStore(Integer id);
	  
	  @Query("select sum(s.userViewed) from storeProducts s where s.store.id = ?1")
	  Integer getViewedProduct(Integer storeId);
	  
	  @Query("select sum(s.userBuyed) from storeProducts s where s.store.id = ?1")
	  Integer getBuyedProduct(Integer storeId);
	  
	  @Query("select s from storeProducts s where s.store.id = ?1 and s.lastBuyedDate IS NOT NULL ORDER BY s.lastBuyedDate DESC")
	  List<storeProducts> getSoldOutProduct(Integer storeId);
	  
	  
}