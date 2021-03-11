package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.VolumeModel;

/**
 * Created by Hp on 11/27/2017.
 */
@Repository
public interface VolumeRepository extends CrudRepository<VolumeModel, Long> {

    public VolumeModel findFirstByOrderByIdDesc();
}
