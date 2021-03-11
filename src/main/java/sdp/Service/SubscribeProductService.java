package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.BaseModel;
import sdp.Model.SubscribeProductModel;
import sdp.Repository.SubscribeProductRepository;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class SubscribeProductService {

    @Autowired
    SubscribeProductRepository subscribeProductRepository;

    public long getLastId(){
        SubscribeProductModel sm = subscribeProductRepository.findFirstByOrderByIdDesc();
        if(sm!=null){
            return sm.getId();
        }
        return 0;
    }

    public long addSubscribeProduct(SubscribeProductModel subscribeProductModel){
        return subscribeProductRepository.save(subscribeProductModel).getId();
    }

   public SubscribeProductModel getId(long id){

        Collection<SubscribeProductModel> portfolioModels = new ArrayList<SubscribeProductModel>();
        portfolioModels =subscribeProductRepository.findById(id);
        return portfolioModels.iterator().next();

    }


    public SubscribeProductModel getByPortfolio(BaseModel baseModel){
        return subscribeProductRepository.findFirstByBaseModelOrderByIdDesc(baseModel);
    }

    public SubscribeProductModel findSubProductByBaseAndProId(BaseModel bm, String productId) {
        return subscribeProductRepository.findTop1ByBaseModelAndProductIdOrderByIdDesc(bm, productId);
    }






}
