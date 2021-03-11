package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ContentModel;
import sdp.Model.PortfolioModel;
import sdp.Repository.ContentRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/26/2017.
 */
@Service
public class ContentService {

    @Autowired
    ContentRepository contentRepository;

    public ContentModel getContentByDateAndPortfolio(Timestamp dateDelivery, PortfolioModel portfolio){
        ContentModel content = contentRepository.findByDateAndPortfolio(dateDelivery, portfolio);
        return content;
    }

    public ContentModel addContent(ContentModel contentModel) {
        return contentRepository.save(contentModel);
    }

    public Collection<ContentModel> findAllContent() {
        Collection<ContentModel> contentModels = new ArrayList<>();
        contentRepository.findAll().forEach(contentModels::add);
        return contentModels;
    }

    public ContentModel findContent(long contentId) {
        return contentRepository.findOne(contentId);
    }

    public void deleteContent(ContentModel contentModel) {
        contentRepository.delete(contentModel);
    }

}
