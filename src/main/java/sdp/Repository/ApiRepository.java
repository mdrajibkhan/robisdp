package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ApiModel;

/**
 * Created by Atiq on 1/8/2018.
 */
@Repository
public interface ApiRepository extends CrudRepository<ApiModel,Long> {
    public ApiModel findByName(String name);
}
