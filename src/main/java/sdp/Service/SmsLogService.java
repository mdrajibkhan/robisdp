package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.SmsLogModel;
import sdp.Repository.SmsLogRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/26/2017.
 */
@Service
public class SmsLogService {

    @Autowired
    SmsLogRepository smsLogRepository;

    public long addSmsLog(SmsLogModel smsLogModel){
        return smsLogRepository.save(smsLogModel).getId();
    }

    public SmsLogModel getId(long id){
        Collection<SmsLogModel> portfolioModels = new ArrayList<SmsLogModel>();
        portfolioModels = smsLogRepository.findById(id);
        return portfolioModels.iterator().next();
    }

    public SmsLogModel getByPortfolio(PortfolioModel portfolioModel){
        return smsLogRepository.findFirstByPortfolioModelOrderByIdDesc(portfolioModel);
    }


    public SmsLogModel getByPortfolioAndBase(PortfolioModel portfolioModel, BaseModel baseModel){
        ArrayList<SmsLogModel> smsLogModels = smsLogRepository.findByPortfolioAndBase(portfolioModel, baseModel);
        if(smsLogModels.size()>0){
            return smsLogModels.get(0);
        }
        return null;
    }

    public long getLastId(){
        long id = 0;
        try {
            SmsLogModel smsLogModel = smsLogRepository.findFirstByOrderByIdDesc();
            if (smsLogModel != null) {
                id = smsLogModel.getId();
            }
            return id;
        }
        catch (Exception ex){
            return id;
        }
    }

    public long updateDeliveryStatus(SmsLogModel smsLogModel){
        long id = 0;
        SmsLogModel model = smsLogRepository.save(smsLogModel);
        if(model!=null){
            return  model.getId();
        }
        return 0;
    }

    public SmsLogModel getSmsLogByCorrelator(String correlator){
        return smsLogRepository.findByCorrelator(correlator);
    }

}
