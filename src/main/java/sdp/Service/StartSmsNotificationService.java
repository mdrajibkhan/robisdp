package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.PortfolioModel;
import sdp.Model.StartSmsNotificationModel;
import sdp.Repository.StartSmsNotificationRepository;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Atiq on 11/26/2017.
 */
@Service
public class StartSmsNotificationService {
    @Autowired
    StartSmsNotificationRepository startSmsNotificationRepository;

    public long addStartSmsNotification(StartSmsNotificationModel ssn){
        return startSmsNotificationRepository.save(ssn).getId();
    }
    public long updateStartSmsNotification(StartSmsNotificationModel ssnm) {
        return startSmsNotificationRepository.save(ssnm).getId();
    }
    public StartSmsNotificationModel findStartSmsNotificationModelByPfm(PortfolioModel pmf) {
        StartSmsNotificationModel sm = new StartSmsNotificationModel();
        sm = startSmsNotificationRepository.findTop1ByPortfolioModelOrderByIdDesc(pmf);
        return sm;
    }

    public long getLastId(){
        StartSmsNotificationModel notification = startSmsNotificationRepository.findFirstByOrderByIdDesc();
        if(notification!=null){
            notification.getId();
        }
        return 0;
    }
}
