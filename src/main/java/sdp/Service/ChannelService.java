package sdp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdp.Model.ChannelModel;
import sdp.Repository.ChannelRepository;

import java.util.ArrayList;

/**
 * Created by Hp on 11/1/2017.
 */
@Service
public class ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    public ChannelModel getChannelByCode(String code){
        return channelRepository.findByCode(code);
    }
    public ChannelModel addChannel(ChannelModel channelModel){
        return channelRepository.save(channelModel);
    }
       public ArrayList<ChannelModel> getAllChannel() {
        ArrayList<ChannelModel> channelModel = new ArrayList<>();
        channelRepository.findAll().forEach(channelModel::add);
        return channelModel;
    }



    public ChannelModel getChannelByTitle(String title){
        return channelRepository.findByTitle(title);
    }
    public ChannelModel getChannelById(long id){
        return channelRepository.findOne(id);
    }



}
