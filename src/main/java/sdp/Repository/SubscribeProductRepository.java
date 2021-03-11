package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseModel;
import sdp.Model.SubscribeProductModel;

import java.util.Collection;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface SubscribeProductRepository extends CrudRepository<SubscribeProductModel, Long> {
   public SubscribeProductModel findFirstByOrderByIdDesc();
   public SubscribeProductModel findFirstByBaseModelOrderByIdDesc(BaseModel baseModel);
   public Collection<SubscribeProductModel> findById(long id);
   public SubscribeProductModel findTop1ByBaseModelAndProductIdOrderByIdDesc(BaseModel baseModel,String productId);
}
