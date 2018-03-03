package software2project.repository;

import org.springframework.data.repository.CrudRepository;
import software2project.models.product;

/**
 * This is Product Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface productRepository extends CrudRepository<product, Integer> {

}