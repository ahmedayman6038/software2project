package software2project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import software2project.models.user;

/**
 * This is User Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface userRepository extends CrudRepository<user, String> {
	@Query("select s from user s where s.email = ?1")
    List<user> findByEmail(String email);
    
    @Query("select s from user s where s.email = ?1 and s.password = ?2")
    List<user> findUser(String email,String password);

    @Query("select s from user s where s.email = ?1 and s.type = ?2")
    List<user> checkType(String email,String type);
}