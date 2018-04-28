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
	  public  List<normalStore> getNormalStores(Integer userId);
	  
	  @Query("select s from onlineStore s where s.user.id = ?1 and accepted = true")
	  public List<onlineStore> getOnlineStores(Integer userId);
	  
	  @Query("select s from normalStore s")
	  public List<normalStore> getAllNormalStores();
	  
	  @Query("select s from onlineStore s")
	  public List<onlineStore> getAllOnlineStores();
	  
	  @Query("select s from normalStore s where s.id = ?1")
	  public  normalStore getNormalStore(Integer id);
		  
	  @Query("select s from onlineStore s where s.id = ?1")
	  public onlineStore getOnlineStore(Integer id);
	  
	  @Query("select sum(s.userViewed) from storeProducts s where s.store.id = ?1")
	  public Integer getViewedProduct(Integer storeId);
	  
	  @Query("select count(s) from buyProducts s where s.store.id = ?1")
	  public  Integer getBuyedProduct(Integer storeId);
	  
	  @Query("select count(s) from storeProducts s where s.store.id = ?1")
	  public Integer getProductsInStore(Integer storeId);
	  
	  @Query("select s from store s where s.id = ?1")
	  public store findById(Integer id);
	  
	  @Query("select s from storeProducts s where s.store.id = ?1 and s.lastBuyedDate IS NOT NULL ORDER BY s.lastBuyedDate DESC")
	  public  List<storeProducts> getSoldOutProduct(Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.price = ?1, s.quantity = ?2, s.brand.id = ?3 where s.product.id = ?4 and s.store.id = ?5")
	  public void updateStoreProducts(Float price,Integer quantity,Integer brandId,Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("delete storeProducts s where s.product.id = ?1 and s.store.id = ?2")
	  public void deleteStoreProducts(Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.offer = null where s.product.id = ?1 and s.store.id = ?2")
	  public  void deleteOffer(Integer productId,Integer storeId);
	  
	  @Transactional
	  @Modifying
	  @Query("update storeProducts s set s.offer = ?1 where s.product.id = ?2 and s.store.id = ?3")
	  public void addOffer(Integer offer,Integer productId,Integer storeId);
	  
}