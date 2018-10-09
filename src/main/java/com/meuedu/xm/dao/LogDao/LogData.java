package com.meuedu.xm.dao.LogDao;

import com.meuedu.xm.dao.Logdata;
import com.meuedu.xm.entity.Ip;
import com.meuedu.xm.entity.log;
import com.meuedu.xm.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LogData implements Logdata{
    @Override
    public List<log> findByVideo() {
        List<log> list=new ArrayList<log>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        log log;
        try {
            connection = JDBCUtils.getConnection();
            statement=connection.createStatement();
            String sql="select collect_set(type),id,count(*) sum from log where type='"+"video"+"' group by id order by sum desc";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                //String type=resultSet.getString("type");
                String id=resultSet.getString("id");
                Integer count=resultSet.getInt("sum");
                log=new log();
                log.setId(id);
                log.setCount(count);
                list.add(log);
                //System.out.println(count);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                System.out.println("12315131");
                JDBCUtils.close(connection,statement,resultSet);
            } catch (SQLException e) {
            }
        }
        return list;
    }
    public List<log> findByArticle(){
        List<log> list=new ArrayList<log>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        log log=new log();
        try {
            connection = JDBCUtils.getConnection();
            statement=connection.createStatement();
            String sql="select collect_set(type),id,count(*) sum from log where type='"+"article"+"' group by id order by sum desc";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                String id=resultSet.getString("id");
                Integer count=resultSet.getInt("sum");
                log=new log();
                log.setId(id);
                log.setCount(count);
                list.add(log);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                System.out.println("12315131");
                JDBCUtils.close(connection,statement,resultSet);
            } catch (SQLException e) {
            }
        }
        return list;
    }
    public List<log> findByTraffic(){
        List<log> list=new ArrayList<log>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        log log=new log();
        try {
            connection = JDBCUtils.getConnection();
            statement=connection.createStatement();
            String sql="select traffic,count(*) sum from log group by traffic order by sum desc";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Integer traffic=resultSet.getInt("traffic");
                Integer count=resultSet.getInt("sum");
                log=new log();
                log.setTraffic(traffic);
                log.setCount(count);
                list.add(log);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                System.out.println("12315131");
                JDBCUtils.close(connection,statement,resultSet);
            } catch (SQLException e) {
            }
        }
        return list;
    }
    public List<log> findByIp(){
        List<log> list=new ArrayList<log>();
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        log log=new log();
        try {
            connection = JDBCUtils.getConnection();
            statement=connection.createStatement();
            String sql="select ip,count(*) sum from log group by ip order by sum desc";
            resultSet=statement.executeQuery(sql);
            Ip i=new Ip();
            while(resultSet.next()){
                String ip= resultSet.getString("ip");
                String city=null;
                try {
                    city = i.result(ip);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Integer count=resultSet.getInt("sum");
                log=new log();
                log.setIp(ip);
                log.setCount(count);
                log.setCity(city);
                list.add(log);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                //System.out.println("12315131");
                JDBCUtils.close(connection,statement,resultSet);
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
