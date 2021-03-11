package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ApiModel;
import sdp.Repository.ApiRepository;

/**
 * Created by Atiq on 1/8/2018.
 */
@Service
public class ApiService {
    @Autowired
    ApiRepository apiRepository;

    public ApiModel getApiModelByName(String name) {
       ApiModel apiModel = apiRepository.findByName(name);
       return apiModel;
    }
}
