package sdp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sdp.Model.PortfolioModel;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Atiq on 11/22/2017.
 */

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioModel, Long> {

    public Collection<PortfolioModel> findByServiceId(String serviceId);


    public PortfolioModel findById(long id);


    public Collection<PortfolioModel> findByProductId(String productId);
public PortfolioModel findByProductName(String productName);

public ArrayList<PortfolioModel> findByServiceName(String serviceName);


    public PortfolioModel findByServiceNameAndTypeOrderByIdDesc(String serviceName,String type);

}
