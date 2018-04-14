package software2project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
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
	  
	  @Query("select count(s) from buyProducts s where s.store.id = ?1")
	  Integer getBuyedProduct(Integer storeId);
	  
	  @Query("select count(s) from storeProducts s where s.store.id = ?1")
	  Integer getProductsInStore(Integer storeId);
	  
	  @Query("select s from store s where s.id = ?1")
	  store findById(Integer id);
	  
	  @Query("select s from storeProducts s where s.store.id = ?1 and s.lastBuyedDate IS NOT NULL ORDER BY s.lastBuyedDate DESC")
	  List<storeProducts> getSoldOutProduct(Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.price = ?1, s.quantity = ?2, s.brand.id = ?3 where s.product.id = ?4 and s.store.id = ?5")
	  void updateStoreProducts(Float price,Integer quantity,Integer brandId,Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("delete storeProducts s where s.product.id = ?1 and s.store.id = ?2")
	  void deleteStoreProducts(Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.offer = null where s.product.id = ?1 and s.store.id = ?2")
	  void deleteOffer(Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.offer = ?1 where s.product.id = ?2 and s.store.id = ?3")
	  void addOffer(Integer offer,Integer productId,Integer storeId);
	  
}