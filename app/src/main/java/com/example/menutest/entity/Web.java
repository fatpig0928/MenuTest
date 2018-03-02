package com.example.menutest.entity;

import java.util.List;

/**
 * 网络释义
 */

public class Web {
    public String key;
    public List<String> values;

    @Override
    public String toString() {

        return key+":"+getAllWebValue();
    }

    public String getAllWebValue(){
        StringBuffer buf=new StringBuffer();
        if (null!=values&&values.size()>0){
            for (int i=0;i<values.size();i++){
                buf.append(values.get(i)).append(",");  //每个值用逗号隔开
            }
            return buf.deleteCharAt(buf.length()-1).toString();  //把最后一逗号去掉
        }
        return "无";
    }
}
