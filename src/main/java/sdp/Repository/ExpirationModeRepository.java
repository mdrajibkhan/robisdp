package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ExpirationModeModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface ExpirationModeRepository extends CrudRepository<ExpirationModeModel, Long> {

    public ExpirationModeModel findByCode(String code);

}
