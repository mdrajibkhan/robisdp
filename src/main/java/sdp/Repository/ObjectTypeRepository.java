package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ObjectTypeModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface ObjectTypeRepository extends CrudRepository<ObjectTypeModel, Long> {

    public ObjectTypeModel findByCode(String code);

}
