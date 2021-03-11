package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.TestModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface TestRepository extends CrudRepository<TestModel, Long> {

    public TestModel findFirstByOrderByIdDesc();

}
