package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ChargeModeModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface ChargeModeRepository extends CrudRepository<ChargeModeModel, Long> {

    public ChargeModeModel findByCode(String code);

}
