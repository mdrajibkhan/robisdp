package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.NotifySmsDeliveryReceiptModel;
import sdp.Repository.NotifyDeliveryRepository;

/**
 * Created by Suvonkar Kundu on 11/6/2017.
 */
@Service
public class NotifyDeliveryService {
    @Autowired
    NotifyDeliveryRepository ndr;

    public  void addNotify(NotifySmsDeliveryReceiptModel smsRequestmodel) {
        ndr.save(smsRequestmodel);
    }
}
