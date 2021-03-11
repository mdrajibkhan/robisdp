package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ObjectTypeModel;
import sdp.Model.UpdateTypeModel;
import sdp.Repository.UpdateTypeRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class UpdateTypeService {

    @Autowired
    UpdateTypeRepository updateTypeRepository;

    public UpdateTypeModel getUpdateTypeByCode(String code){
        return updateTypeRepository.findByCode(code);
    }

    public UpdateTypeModel addUpdateType(UpdateTypeModel updateTypeModel) {
        return updateTypeRepository.save(updateTypeModel);

    }
    public UpdateTypeModel findUpdateType(long updateTypeId) {
        return updateTypeRepository.findOne(updateTypeId);
    }
    public void deleteUpdateType(UpdateTypeModel updateTypeModel) {
        updateTypeRepository.delete(updateTypeModel);
    }
    public Collection<UpdateTypeModel> findAllUpdateType() {
        Collection<UpdateTypeModel> updateTypes = new ArrayList<>();
        updateTypeRepository.findAll().forEach(updateTypes::add);
        return updateTypes;
    }

}
