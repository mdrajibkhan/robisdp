package sdp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sdp.Model.BaseModel;
import sdp.Model.PortfolioModel;
import sdp.Model.StartSmsNotificationModel;
import sdp.Model.SyncRelation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hp on 10/29/2017.
 */
@Repository
public interface SyncRelationRepository extends CrudRepository<SyncRelation, Long> {

    public SyncRelation findFirstByOrderByIdDesc();

    public Collection<SyncRelation> findByBaseModelOrderByIdDesc(BaseModel baseModel);

    public SyncRelation findBaseModelById(long id);

    public Collection<SyncRelation> findById(long id);

    public SyncRelation findTop1ByBaseModelAndProductIdOrderByIdDesc(BaseModel baseModel,String productId);

    public SyncRelation findTop1ByBaseModelAndServiceIdOrderByIdDesc(BaseModel baseModel,String serviceId);

    @Query("select u from SyncRelation u where u.baseModel = ?1 and u.serviceId = ?2 and ( u.updateDesc = 'Addition' or u.updateDesc = 'Deletion' ) order by u.id desc")
    public Collection<SyncRelation> findTop1ByBaseModelAndServiceId(BaseModel baseModel, String serviceId);

    @Query("select u from SyncRelation u where u.serviceId = ?1 order by u.id desc")
    public ArrayList<SyncRelation> findSyncDataByServiceId(String serviceId);


}
