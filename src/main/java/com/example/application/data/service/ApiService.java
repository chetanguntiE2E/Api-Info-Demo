package com.example.application.data.service;

import com.example.application.data.entity.Apiinfo;
import com.example.application.data.entity.Apitype;
import com.example.application.data.repository.ApiinfoRepository;
import com.example.application.data.repository.ApitypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {
    private final ApiinfoRepository apiinfoRepository;
    private final ApitypeRepository apitypeRepository;
    public ApiService(ApiinfoRepository apiinfoRepository, ApitypeRepository apitypeRepository) {

        this.apiinfoRepository= apiinfoRepository;
        this.apitypeRepository= apitypeRepository;
    }
    public List<Apiinfo> findAllApiinfos(String filterText){
        if(filterText==null || filterText.isEmpty()){
            return apiinfoRepository.findAll();

        } else {
            return apiinfoRepository.search(filterText);

        }

    }
    public long countApiinfos(){
        return apiinfoRepository.count();

    }
    public void deleteApiinfo(Apiinfo apiinfo){
        apiinfoRepository.delete(apiinfo);
    }
    public void saveApiinfo(Apiinfo apiinfo){
        if(apiinfo==null){
            System.err.println("API Info is null");
            return;
        }
        apiinfoRepository.save(apiinfo);
    }
    public List<Apitype> findAllApitypes(){
        return apitypeRepository.findAll();
    }
}
