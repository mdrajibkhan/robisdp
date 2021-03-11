package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ExpirationModeModel;
import sdp.Repository.ExpirationModeRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class ExpirationModeService {

    @Autowired
    ExpirationModeRepository expirationModeRepository;

    public ExpirationModeModel getExpirationModeByCode(String code){
        return expirationModeRepository.findByCode(code);
    }
    public ExpirationModeModel addExpirationMode(ExpirationModeModel expirationModeModel){
        return expirationModeRepository.save(expirationModeModel);
    }

    public ArrayList<ExpirationModeModel> getAllExpirationMode() {
        ArrayList<ExpirationModeModel> expirationModeModel = new ArrayList<>();
        expirationModeRepository.findAll().forEach(expirationModeModel::add);
        return expirationModeModel;
    }
    public ExpirationModeModel getExpirationModeById(long id){
        return expirationModeRepository.findOne(id);
    }

}
