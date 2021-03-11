package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.AmountModel;
import sdp.Repository.AmountRepository;

/**
 * Created by Hp on 11/26/2017.
 */
@Service
public class AmountService {

    @Autowired
    AmountRepository chargeAmountRepository;

    public long addChargeAmount(AmountModel chargeAmount){
        return chargeAmountRepository.save(chargeAmount).getId();
    }

    public long getLastId(){
        AmountModel model = chargeAmountRepository.findFirstByOrderByIdDesc();
        if(model!=null){
            return model.getId();
        }
        return 0;
    }

}
