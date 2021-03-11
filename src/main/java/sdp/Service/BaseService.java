package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.BaseModel;
import sdp.Repository.BaseRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class BaseService {

    @Autowired
    BaseRepository baseRepository;

    public BaseModel isBaseExists(String mdn){
        BaseModel baseModel = baseRepository.findByMdn(mdn);
        return baseModel;
    }
    public ArrayList<BaseModel> getAllListBase() {
        ArrayList<BaseModel> baseModels = new ArrayList<>();
        baseRepository.findAll().forEach(baseModels::add);
        return baseModels;
    }

    public long addBase(BaseModel baseModel){
        return baseRepository.save(baseModel).getId();
    }

//    public BaseModel addBase1(BaseModel baseModel){
//        return baseRepository.save(baseModel);
//    }


    public long getLastId(){
        BaseModel baseModel = baseRepository.findFirstByOrderByIdDesc();
        long id = 0;
        if(baseModel!=null){
            id = baseModel.getId();
        }
        return id;
    }



    public long getBaseId(String mdn) {
        BaseModel base = baseRepository.findByMdn(mdn);
        if (base != null) {
            return base.getId();
        } else {
            return 0;
        }

    }


        public BaseModel getId(String mdn){
            return baseRepository.findByMdn(mdn);
        }

    public BaseModel getBaseById(long id){
        return baseRepository.findOne(id);
    }



    }






