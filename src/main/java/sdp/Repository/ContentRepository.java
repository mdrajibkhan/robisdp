package sdp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.ContentModel;
import sdp.Model.PortfolioModel;

import java.sql.Timestamp;

/**
 * Created by Hp on 11/26/2017.
 */
@Repository
public interface ContentRepository extends CrudRepository<ContentModel, Long> {

    @Query("select u from ContentModel u where u.dateDelivery <= ?1 and u.dateExpiry >= ?1 AND u.portfolioModel = ?2")
    public ContentModel findByDateAndPortfolio(Timestamp date, PortfolioModel portfolio);

}
