package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.TestModel;
import sdp.Repository.TestRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 10/29/2017.
 */
@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public long addTest(TestModel testModel){
        return testRepository.save(testModel).getId();
    }

    public TestModel updateTest(TestModel testModel){
        return testRepository.save(testModel);
    }

    public long getLastTestId(){
        TestModel employeeModel = testRepository.findFirstByOrderByIdDesc();
        long id = 0;
        if(employeeModel!=null){
            id = employeeModel.getId();
        }
        return 0;
    }

    public TestModel getTest(long id){
        return testRepository.findOne(id);
    }

    public ArrayList<TestModel> getAllTest(){
        ArrayList<TestModel> models = new ArrayList<>();
        testRepository.findAll().forEach(models::add);
        return models;
    }

}
