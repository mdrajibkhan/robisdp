package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ChannelModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface ChannelRepository extends CrudRepository<ChannelModel, Long> {

    public ChannelModel findByCode(String code);
public ChannelModel findByTitle(String title);
}
