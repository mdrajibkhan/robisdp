package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.HeaderModel;

/**
 * Created by Hp on 11/21/2017.
 */
@Repository
public interface HeaderRepository extends CrudRepository<HeaderModel, Long> {

    public HeaderModel findByKey(String key);

}
