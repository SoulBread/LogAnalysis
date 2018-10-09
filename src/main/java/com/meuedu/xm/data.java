package com.meuedu.xm;


import com.meuedu.xm.entity.log;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class data {
    public static void main(String[] args) throws IOException {
        read();
    }
    public static List<log> read() throws IOException{
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:\\\\workspace\\\\test\\\\10000_access.log"))));
        String line="";
        List<log> list=new ArrayList<>();
        int count=0;
        log l;
        while(line!=null) {
            line = bufferedReader.readLine();
            if (!line.equals("")) {
                int indexIP1=getCharacterPosition(line," ",1);
                int time1=getCharacterPosition(line," ",3);
                int time2=getCharacterPosition(line," ",5);
                int traffic1=getCharacterPosition(line," ",9);
                int traffic2=getCharacterPosition(line," ",10);
                int type1=getCharacterPosition(line,"\"",5);
                int type2=getCharacterPosition(line,"\"",6);
                String stringIP=line.substring(0,indexIP1);
                String stringTime=line.substring(time1+2,time2-1);
                String stringTraffic=line.substring(traffic1+1,traffic2);
                String stringType=line.substring(type1+1,type2);
                if(stringIP!=null&&stringTime!=null&&stringTraffic!=null&&stringType!=null) {
                    if (stringType.contains("com/article/") || stringType.contains("com/video/")) {
                        String ip = stringIP;
                        String time = replaceTime(stringTime);
                        String day = getDay(time);
                        String typ = replaceType(stringType);
                        int typ1 = getCharacterPosition(typ, "/", 1);
                        String noid = typ.substring(typ1 + 1);
                        if (!getId(noid)) {
                            String type = typ.substring(0, typ1);
                            count++;
                            int a = Integer.parseInt(stringTraffic);
                            l = new log(ip, time, day, a, type, noid);
                            list.add(l);
                            write(list);
                            //System.out.println(count);
                            //System.out.println(ip + " time:" + time + " day:" + day + " traffic:" + stringTraffic + " type:" + type + " id:" + noid);
                            //return list;
                        }
                    }
                }
            }
        }
        return list;
    }
    public static void write(List<log> list){
        System.out.println(list);
        Configuration configuration=new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.64.110:9000");
        configuration.set("dfs.client.block.write.replace-datanode-on-failure.policy",
                "NEVER"
        );

        configuration.set("dfs.client.block.write.replace-datanode-on-failure.enable",
                "true"
        );
        try {
            FileSystem fs=FileSystem.get(configuration);
            FSDataOutputStream outputStream = fs.create(new Path("/hadoop/log.txt"));
            if(!list.isEmpty()) {
                for (log l: list) {
                    outputStream.writeBytes(l.getIp() + "\t" + l.getTime() + "\t" + l.getDay()
                            + "\t" + l.getTraffic() + "\t" + l.getType() + "\t" + l.getId()+"\n");
                }
            }
            System.out.println("assaa");
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static boolean getId(String str){
        boolean flag=false;
        for(int i=0;i<str.length();i++){
            char x=str.charAt(i);
            if((x>'a'&&x<'z')||(x>'A'&&x<'Z')||(x=='/')){
                flag=true;
                return flag;
            }
        }
        return flag;
    }
    public static String getDay(String s){
        int a=getCharacterPosition(s,"-",2);
        int b=getCharacterPosition(s,":",1);
        return s.substring(a+1,b);
    }
    public static String replaceTime(String str){
        int first=getCharacterPosition(str,"/",1);
        int second=getCharacterPosition(str,"/",2);
        int third=getCharacterPosition(str,":",1);
        String time=str.substring(second+1,third)+"-"+date(str.substring(first+1,second))+"-"+str.substring(0,first);
        int aa=getCharacterPosition(str," ",1);
        String t=str.replaceAll("-",":").substring(third+1,aa);
        return time+" "+t;
    }
    public static String replaceType(String str){
        int a=getCharacterPosition(str,"/",3);
        String s=str.substring(a+1);
        return s;
    }
    public static String date(String str){
        if(str.equals("Nov")){
           str="11";
        }
        return str;
    }
    private static   int  getCharacterPosition(String line,String operation,int index){
        Matcher slashMatcher= Pattern.compile(operation).matcher(line);
        int mIndex=0;
        while(slashMatcher.find()){
            mIndex++;
            if(mIndex==index){
                break;
            }
        }
        return slashMatcher.start();
    }
}
