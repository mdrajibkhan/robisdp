package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface BaseRepository extends CrudRepository<BaseModel, Long> {

    public BaseModel findByMdn(String mdn);

    public BaseModel findFirstByOrderByIdDesc();

}
