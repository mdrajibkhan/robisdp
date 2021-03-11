package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.LanguageModel;
import sdp.Repository.LanguageRepository;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    public LanguageModel getLanguageByTitle(String title){
        return languageRepository.findByTitle(title);
    }
}
