package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.UpdateTypeModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface UpdateTypeRepository extends CrudRepository<UpdateTypeModel, Long> {

    public UpdateTypeModel findByCode(String code);

}
