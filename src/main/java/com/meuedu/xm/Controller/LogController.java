package com.meuedu.xm.Controller;

import com.alibaba.fastjson.JSONObject;
import com.meuedu.xm.Service.LogService;
import com.meuedu.xm.entity.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(value="/video")
    public List<log> findByVideo(){
        return logService.findByVideo();
    }
    @RequestMapping(value="/article")
    public List<log> findByArticle(){
        return logService.findByArticle();
    }
    @RequestMapping(value="/traffic")
    public List<log> findByTraffic(){
        return logService.findByTraffic();
    }
    @RequestMapping(value="/ip")
    public List<log> findByIp(){
        return logService.findByIp();
    }
}
