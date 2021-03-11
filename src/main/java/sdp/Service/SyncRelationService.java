package sdp.Service;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import sdp.Model.BaseModel;
import sdp.Model.SyncRelation;
import sdp.Repository.SyncRelationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class SyncRelationService {

    @Autowired
    SyncRelationRepository syncRelationRepository;

    public long addSyncRelation(SyncRelation syncRelation){
        return syncRelationRepository.save(syncRelation).getId();
    }

    public long getLastId(){
        SyncRelation syncRelation = syncRelationRepository.findFirstByOrderByIdDesc();
        long id = 0;
        if(syncRelation!=null){
            id = syncRelation.getId();
        }
        return id;
    }


    public SyncRelation getId(long id){
        Collection<SyncRelation> portfolioModels = new ArrayList<SyncRelation>();
        portfolioModels =syncRelationRepository.findById(id);
        return portfolioModels.iterator().next();

    }

    public SyncRelation getSyncRelationByBase(BaseModel baseModel) {
        Collection<SyncRelation> syncRelations = syncRelationRepository.findByBaseModelOrderByIdDesc(baseModel);
        return  syncRelations.iterator().next();
    }

    public SyncRelation findSynRelationModelByBase(BaseModel bm, String productId) {
        return syncRelationRepository.findTop1ByBaseModelAndProductIdOrderByIdDesc(bm, productId);
    }

    public SyncRelation findSynRelationModelByService(BaseModel bm, String serviceId) {
        return syncRelationRepository.findTop1ByBaseModelAndServiceIdOrderByIdDesc(bm, serviceId);
    }

    public SyncRelation findSynRelationModelByServiceAndBase(BaseModel bm, String serviceId) {
        Collection<SyncRelation> syncRelations = syncRelationRepository.findTop1ByBaseModelAndServiceId(bm, serviceId);
        if(syncRelations.size()>0)
            return syncRelations.iterator().next();
        else
            return null;
    }

    public SyncRelation findSyncRelationByServiceId(String serviceId){
        ArrayList<SyncRelation> syncRelations = syncRelationRepository.findSyncDataByServiceId(serviceId);
        if(syncRelations.size()>0){
            return syncRelations.get(0);
        }
        return null;
    }

}
