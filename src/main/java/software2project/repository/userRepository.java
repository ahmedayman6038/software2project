package software2project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import software2project.models.buyProducts;
import software2project.models.user;

/**
 * This is User Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface userRepository extends CrudRepository<user, String> {
	@Query("select s from user s where s.email = ?1")
	public user findByEmail(String email);
    
    @Query("select s from user s where s.email = ?1 and s.password = ?2")
    public user findUser(String email,String password);

    @Query("select s from user s where s.email = ?1 and s.type = ?2")
    public user checkType(String email,String type);
    
    @Query("select s from buyProducts s where s.user.id = ?1")
    public List<buyProducts> checkFirstBuy(Integer uid);
    
    @Query("select s from user s where s.id = ?1")
    public user findById(Integer id);
    
}