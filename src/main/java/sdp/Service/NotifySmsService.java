package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.NotifySms;
import sdp.Repository.NotifySmsRepository;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class NotifySmsService {

    @Autowired
    NotifySmsRepository notifySmsRepository;

    public long addNotify(NotifySms notifySms) {
        return notifySmsRepository.save(notifySms).getId();
    }

    public long getLastId(){
        NotifySms notifySms = notifySmsRepository.findFirstByOrderByIdDesc();
        if(notifySms!=null){
            return  notifySms.getId();
        }
        return 0;
    }


}
