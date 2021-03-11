package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ChargeModeModel;
import sdp.Repository.ChargeModeRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class ChargeModeService {

    @Autowired
    ChargeModeRepository chargeModeRepository;

    public ChargeModeModel getChargeModeByCode(String code){
        return chargeModeRepository.findByCode(code);
    }
    public ChargeModeModel addChargeMode(ChargeModeModel chargeModeModel){
        return chargeModeRepository.save(chargeModeModel);
    }
    public ArrayList<ChargeModeModel> getAllChargeModeData() {
        ArrayList<ChargeModeModel> channelModel = new ArrayList<>();
        chargeModeRepository.findAll().forEach(channelModel::add);
        return channelModel;
    }
    public ChargeModeModel getChargeModeById(long id){
        return chargeModeRepository.findOne(id);
    }
}
