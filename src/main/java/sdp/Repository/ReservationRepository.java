package sdp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.ReservationModel;

import java.util.ArrayList;

/**
 * Created by Hp on 11/27/2017.
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservationModel, Long> {

    public ReservationModel findFirstByOrderByIdDesc();

    @Query("select u FROM ReservationModel u WHERE u.portfolioModel = ?1 AND u.baseModel = ?2 order by u.id desc")
    public ArrayList<ReservationModel> findReservedAmount(PortfolioModel portfolioModel, BaseModel baseModel);

}
