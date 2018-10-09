package com.meuedu.xm.entity;


public class log {
    private String ip;
    private String time;
    private String day;
    private int traffic;
    private String type;
    private String id;
    private Integer count;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public log() {
    }

    public log(String ip, String time, String day, int traffic, String type, String id) {
        this.ip = ip;
        this.time = time;
        this.day = day;
        this.traffic = traffic;
        this.type = type;
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count){
        this.count=count;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
