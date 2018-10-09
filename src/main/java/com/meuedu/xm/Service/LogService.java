package com.meuedu.xm.Service;

import com.meuedu.xm.dao.Logdata;
import com.meuedu.xm.entity.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private Logdata logdata;
    public List<log> findByVideo(){
        return logdata.findByVideo();
    }
    public List<log> findByArticle(){
        return logdata.findByArticle();
    }
    public List<log> findByTraffic(){
        return logdata.findByTraffic();
    }
    public List<log> findByIp(){
        return logdata.findByIp();
    }
}
