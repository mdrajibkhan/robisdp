package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.HeaderModel;
import sdp.Repository.HeaderRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/21/2017.
 */
@Service
public class HeaderService {

    @Autowired
    HeaderRepository headerRepository;

    public String getValue(String key){
        HeaderModel headerModel = headerRepository.findByKey(key);
        return  headerModel.getValue();
    }
    public HeaderModel addHeader(HeaderModel headerModel){
        return headerRepository.save(headerModel);
    }

    public ArrayList<HeaderModel> getAllHeader() {
        ArrayList<HeaderModel> headerModel = new ArrayList<>();
        headerRepository.findAll().forEach(headerModel::add);
        return headerModel;
    }

    public HeaderModel getHeaderById(long id){
        return headerRepository.findOne(id);
    }
}
