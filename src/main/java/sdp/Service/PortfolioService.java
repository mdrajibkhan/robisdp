package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.PortfolioModel;
import sdp.Repository.PortfolioRepository;

import java.util.ArrayList;
import java.util.Collection;



/**
 * Created by Atiq on 11/23/2017.
 */
@Service
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

    public PortfolioModel getPortfolioModelByServiceId(String serviceId){
        Collection<PortfolioModel> portfolioModels = new ArrayList<PortfolioModel>();
        portfolioModels = portfolioRepository.findByServiceId(serviceId);
        if(portfolioModels.size()>0)
           return portfolioModels.iterator().next();
        else
            return null;
    }

    public PortfolioModel getPortfolioModelByProductId(String productId){
        Collection<PortfolioModel> portfolioModels = new ArrayList<PortfolioModel>();
        portfolioModels = portfolioRepository.findByProductId(productId);
        if(portfolioModels.size() > 0)
           return portfolioModels.iterator().next();
        else
           return  null;
    }

    public Collection<PortfolioModel> getAllPortfolioModelByServiceId(String serviceId){
        return portfolioRepository.findByServiceId(serviceId);
    }



    public PortfolioModel findPortfolioModel(long id){
        PortfolioModel portfolioModel = portfolioRepository.findById(id);
        return portfolioModel;
    }



    public PortfolioModel getPortfolioModelByProductName(String productName){

    PortfolioModel    portfolioModel = portfolioRepository.findByProductName(productName);
       return  portfolioModel;
    }

    public PortfolioModel getPortfolioModelByServiceName(String serviceName){

        ArrayList<PortfolioModel> portfolioModels = portfolioRepository.findByServiceName(serviceName);
        if(portfolioModels.size()>0){
            return  portfolioModels.get(0);
        }
        return  null;
    }


    public PortfolioModel findPortfolioModelOnDemand(String serviceName, String type) {
        return portfolioRepository.findByServiceNameAndTypeOrderByIdDesc(serviceName,type);
    }

    public PortfolioModel addPortfolio(PortfolioModel portfolioModel) {
       return portfolioRepository.save(portfolioModel);
    }

    public void deletePortfolio(PortfolioModel portfolioModel) {
        portfolioRepository.delete(portfolioModel);
    }

    public Collection<PortfolioModel> findAllPortfolios() {
        Collection<PortfolioModel> portfolioModels = new ArrayList<>();
        portfolioRepository.findAll().forEach(portfolioModels::add);
        return portfolioModels;
    }


}
