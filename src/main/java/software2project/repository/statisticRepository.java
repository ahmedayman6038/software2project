package software2project.repository;

import org.springframework.data.repository.CrudRepository;
import software2project.models.statistic;

/**
 * This is Statistic Repository Responsible for Querying the DataBase
 * @author Rick & morty
 *
 */
public interface statisticRepository extends CrudRepository<statistic, Integer> {

}