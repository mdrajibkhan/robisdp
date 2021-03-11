package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.NotifySmsDeliveryReceiptModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface NotifyDeliveryRepository extends CrudRepository<NotifySmsDeliveryReceiptModel, Long> {

}
