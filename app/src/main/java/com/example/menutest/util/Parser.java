package com.example.menutest.util;

import android.util.Log;

import com.example.menutest.entity.Result;
import com.example.menutest.entity.Web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析结果
 */

public class Parser {

    public static final String TAG=Parser.class.getSimpleName();

    public static Result parserData(String re){

            try {
                JSONObject rootObj = new JSONObject(re);
                if (rootObj.getInt("errorCode") == 0) {
                    Result result = new Result();
                    if (rootObj.has("translation")) {
                        String translation = rootObj.getString("translation");
                        result.translation = translation;
                    }
                    //关键字
                    if (rootObj.has("query")) {
                        String query = rootObj.getString("query");
                        result.query = query;
                    }
                    if (rootObj.has("basic")) {
                        JSONObject basicObj = rootObj.getJSONObject("basic");
                        //美式发音
                        if (basicObj.has("us-phonetic")) {
                            String usPhonetic = basicObj.getString("us-phonetic");
                            result.usPhonetic = usPhonetic;
                        }
                        //英式发音
                        if (basicObj.has("uk-phonetic")) {
                            String ukPhonetic = basicObj.getString("uk-phonetic");
                            result.ukPhonetic = ukPhonetic;
                        }
                        //基本释义
                        if (basicObj.has("explains")) {
                            JSONArray explainsArray = basicObj.getJSONArray("explains");
                            List<String> explains = new ArrayList<String>();
                            for (int i = 0; i < explainsArray.length(); i++) {
                                explains.add(explainsArray.getString(i));
                            }
                            result.explains = explains;
                        }
                    }

                    //网络释义
                    if (rootObj.has("web")) {
                        List<Web> webs = new ArrayList<Web>();
                        JSONArray webArray = rootObj.getJSONArray("web");
                        for (int i = 0; i < webArray.length(); i++) {
                            JSONObject webObj = webArray.getJSONObject(i);
                            Web web = new Web();
                            String key = webObj.getString("key");
                            web.key = key;

                            List<String> values = new ArrayList<String>();
                            JSONArray valueArray = webObj.getJSONArray("value");
                            for (int j = 0; j < valueArray.length(); j++) {
                                values.add(valueArray.getString(i));
                            }
                            web.values = values;
                            webs.add(web);
                        }
                        result.webs = webs;
                    }
                    return result;
                }
            } catch (JSONException e) {
                Log.e(TAG, "JSONException");
            }

        return null;
    }
}
