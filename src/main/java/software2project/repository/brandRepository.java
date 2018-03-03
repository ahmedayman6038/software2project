package software2project.repository;

import org.springframework.data.repository.CrudRepository;
import software2project.models.brand;

/**
 * This is Brand Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface brandRepository extends CrudRepository<brand, Integer> {

}