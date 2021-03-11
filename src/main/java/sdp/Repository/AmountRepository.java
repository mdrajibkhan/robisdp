package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.AmountModel;

/**
 * Created by Hp on 11/26/2017.
 */
@Repository
public interface AmountRepository extends CrudRepository<AmountModel, Long> {

    public AmountModel findFirstByOrderByIdDesc();

}
