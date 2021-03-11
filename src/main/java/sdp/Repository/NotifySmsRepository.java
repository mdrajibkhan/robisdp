package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.NotifySms;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface NotifySmsRepository extends CrudRepository<NotifySms,Long> {

    public NotifySms findFirstByOrderByIdDesc();

}
