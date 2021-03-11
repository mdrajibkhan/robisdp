package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.LanguageModel;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface LanguageRepository extends CrudRepository<LanguageModel, Long> {

    public LanguageModel findByTitle(String title);

}
