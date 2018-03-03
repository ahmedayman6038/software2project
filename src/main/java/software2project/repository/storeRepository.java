package software2project.repository;

import org.springframework.data.repository.CrudRepository;
import software2project.models.store;

/**
 * This is Store Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface storeRepository extends CrudRepository<store, Integer> {

}