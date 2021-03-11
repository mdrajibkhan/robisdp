package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.LogModel;

/**
 * Created by Atiq on 12/5/2017.
 */
@Repository
public interface LogRepository extends CrudRepository<LogModel,Long> {

}
