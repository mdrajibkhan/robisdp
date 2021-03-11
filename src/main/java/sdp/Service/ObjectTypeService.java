package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ObjectTypeModel;
import sdp.Repository.ObjectTypeRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class ObjectTypeService {

    @Autowired
    ObjectTypeRepository objectTypeRepository;

    public ObjectTypeModel getObjectTypeByCode(String code){
        return objectTypeRepository.findByCode(code);
    }

    public ObjectTypeModel addObjectType(ObjectTypeModel objectTypeModel) {
        return objectTypeRepository.save(objectTypeModel);
    }
    public ObjectTypeModel findObjectType(long objectTypeId) {
        return  objectTypeRepository.findOne(objectTypeId);
    }
    public void deleteObjectType(ObjectTypeModel objectTypeModel) {
        objectTypeRepository.delete(objectTypeModel);
    }
    public Collection<ObjectTypeModel> findAllObjectTypes() {
        Collection<ObjectTypeModel> objectTypeModels = new ArrayList<>();
        objectTypeRepository.findAll().forEach(objectTypeModels::add);
        return objectTypeModels;
    }

}