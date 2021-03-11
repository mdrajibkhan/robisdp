package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.LogModel;
import sdp.Model.NotifySms;
import sdp.Repository.LogRepository;

/**
 * Created by Atiq on 12/5/2017.
 */
@Service
public class LogService {
    @Autowired
    LogRepository logRepository;

    public void addLog(LogModel log){
        logRepository.save(log);
    }
    public LogModel addLog1(LogModel log) {
        return logRepository.save(log);
    }
}
