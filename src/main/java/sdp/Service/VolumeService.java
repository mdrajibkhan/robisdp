package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.AmountModel;
import sdp.Model.VolumeModel;
import sdp.Repository.AmountRepository;
import sdp.Repository.VolumeRepository;

/**
 * Created by Hp on 11/26/2017.
 */
@Service
public class VolumeService {

    @Autowired
    VolumeRepository volumeRepository;

    public long chargeVolume(VolumeModel volumeModel){
        return volumeRepository.save(volumeModel).getId();
    }

    public long getLastId(){
        VolumeModel model = volumeRepository.findFirstByOrderByIdDesc();
        if(model!=null){
            return model.getId();
        }
        return 0;
    }

}
