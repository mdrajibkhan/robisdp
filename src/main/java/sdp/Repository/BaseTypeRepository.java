package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseTypeModel;
import sdp.Model.TestModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface BaseTypeRepository extends CrudRepository<BaseTypeModel, Long> {

    public BaseTypeModel findByCode(long code);

    BaseTypeModel findById(long id);
}
