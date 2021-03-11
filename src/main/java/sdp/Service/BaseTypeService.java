package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.BaseTypeModel;
import sdp.Repository.BaseTypeRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 10/31/2017.
 */
@Service
public class BaseTypeService {

    @Autowired
    BaseTypeRepository baseTypeRepository;

    public BaseTypeModel getBaseTypeByCode(long code) {
        return baseTypeRepository.findByCode(code);
    }
    public BaseTypeModel getIdByBaseTypeId(long id){
        return baseTypeRepository.findById(id);
    }
    public BaseTypeModel addBaseType(BaseTypeModel baseTypeModel){
        return baseTypeRepository.save(baseTypeModel);
    }

    public ArrayList<BaseTypeModel> getAllListBaseType() {
        ArrayList<BaseTypeModel> baseTypeModels = new ArrayList<>();
        baseTypeRepository.findAll().forEach(baseTypeModels::add);
        return baseTypeModels;
    }
}
