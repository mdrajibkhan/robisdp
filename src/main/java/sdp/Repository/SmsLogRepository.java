package sdp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.SmsLogModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 11/26/2017.
 */
@Repository
public interface SmsLogRepository extends CrudRepository<SmsLogModel, Long> {

    public Collection<SmsLogModel> findById(long id);

    public SmsLogModel findFirstByOrderByIdDesc();

    public SmsLogModel findByCorrelator(String correlator);

    public SmsLogModel findFirstByPortfolioModelOrderByIdDesc(PortfolioModel portfolioModel);

    @Query("select u from SmsLogModel u where u.portfolioModel = ?1 and u.baseModel = ?2 order by u.id desc")
    public ArrayList<SmsLogModel> findByPortfolioAndBase(PortfolioModel portfolioModel, BaseModel baseModel);


}
