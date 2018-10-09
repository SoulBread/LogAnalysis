package com.meuedu.xm.dao;

import com.meuedu.xm.entity.log;

import java.util.List;

public interface Logdata {
    List<log> findByVideo();
    List<log> findByArticle();
    List<log> findByTraffic();
    List<log> findByIp();
}
