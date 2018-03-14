package software2project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import software2project.models.*;

/**
 * This is Product Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface productRepository extends CrudRepository<product, Integer> {
	 @Query("select s from normalProduct s")
	 List<normalProduct> getNormalProducts();
	 
	 @Query("select s from onlineProduct s")
	 List<onlineProduct> getOnlineProducts();
	 
	 @Query("select s from normalProduct s where s.id = ?1")
	 normalProduct getNormalProduct(Integer id);
	  
	 @Query("select s from onlineProduct s where s.id = ?1")
	 onlineProduct getOnlineProduct(Integer id);
}