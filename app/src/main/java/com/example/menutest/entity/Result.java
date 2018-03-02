package com.example.menutest.entity;

import java.util.List;


public class Result {

    public String query;   //关键字
    public String translation; //有道翻译
    public String usPhonetic;  //美式发音
    public String ukPhonetic;  //英式发音

    public List<String> explains;  //基本释义

    public List<Web> webs;  //网络释义

    @Override
    public String toString() {
        return "关键字："+checkIsEmpty(query)+
                "\n美式发音："+checkIsEmpty(usPhonetic)+
                "\n英式发音："+checkIsEmpty(ukPhonetic)+
                "\n有道翻译："+checkIsEmpty(translation)+
                "\n基本释义：\n"+getExplain()+
                "\n网络释义：\n"+getWeb();
    }

    public String checkIsEmpty(String aa){
        return aa==null?"无":aa;
    }

    public String getExplain(){   //获取基本释义
        StringBuffer buf=new StringBuffer();
        if (null!=explains&&explains.size()>0){
            for (int i=0;i<explains.size();i++){
                buf.append("    ").append(explains.get(i)).append("\n");  //每个值用逗号隔开
            }
            return buf.deleteCharAt(buf.length()-1).toString();  //把最后一逗号去掉
        }
        return "无";
    }

    public String getWeb(){   //获取网络释义
        StringBuffer buf=new StringBuffer();
        if (null!=webs&&webs.size()>0){
            for (int i=0;i<webs.size();i++){
                buf.append("    ").append(webs.get(i).toString()).append("\n");  //每个值用逗号隔开
            }
            return buf.deleteCharAt(buf.length()-1).toString();  //把最后一逗号去掉
        }
        return "无";
    }

}
