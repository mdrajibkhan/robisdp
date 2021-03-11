package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.PortfolioModel;
import sdp.Model.StartSmsNotificationModel;

import java.awt.print.Pageable;
import java.util.Collection;

/**
 * Created by Atiq on 11/26/2017.
 */
@Repository
public interface StartSmsNotificationRepository extends CrudRepository<StartSmsNotificationModel,Long> {

    public Collection<StartSmsNotificationModel> findByPortfolioModel(PortfolioModel portfolioModel);

    public StartSmsNotificationModel findFirstByOrderByIdDesc(PortfolioModel portfolioModel);

    public StartSmsNotificationModel findTop1ByPortfolioModelOrderByIdDesc(PortfolioModel portfolioModel);

    public StartSmsNotificationModel findFirstByOrderByIdDesc();

}
