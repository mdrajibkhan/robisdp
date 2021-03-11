package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.ReservationModel;
import sdp.Repository.ReservationRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/27/2017.
 */
@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public long getLastId(){
        ReservationModel reservationModel = reservationRepository.findFirstByOrderByIdDesc();
        if(reservationModel!=null){
            return  reservationModel.getId();
        }
        return 0;
    }

    public long addReservation(ReservationModel reservationModel){
        return reservationRepository.save(reservationModel).getId();
    }

    public ReservationModel getReservationModelByPortfolioAndBase(PortfolioModel portfolioModel, BaseModel baseModel){
        ArrayList<ReservationModel> reservationModels = reservationRepository.findReservedAmount(portfolioModel, baseModel);
        if(reservationModels.size()>0){
            return reservationModels.get(0);
        }
        return null;
    }

//    public ReservationModel getLastReservationByPortfolioAndBase(PortfolioModel portfolioModel, BaseModel baseModel){
//        ArrayList<ReservationModel> reservationModels =  reservationRepository.findPortfolioAndBase(portfolioModel, baseModel);
//        if(reservationModels.size()>0){
//            return reservationModels.get(0);
//        }
//        return null;
//    }

}
