package com.example.menutest.util;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 网络+异步类的方法
 */

public class AsycHttp {

    public static final String TAG=AsycHttp.class.getSimpleName();//获取当前类的名字
    private static AsycHttp mAsycHttp;
    private AsycHttp(){

    }
    public static AsycHttp getInstance(){
        if (null==mAsycHttp){
            synchronized (AsycHttp.class){
                if (null==mAsycHttp){
                    mAsycHttp=new AsycHttp();
                }
            }
        }
        return mAsycHttp;
    }

    /**
     * GET请求
     * @param path 路径
     * @param input 输入关键字
     */
    public void requestForGet(String path,String input,onResponseListner monResponseListner){

        //执行网络请求
        if (null!=input||"".equals(input)){
            StringBuffer buf=new StringBuffer();
            buf.append(path).append(input);                 //地址和输入的关键字拼接，得到查询结果
            HttpGet request=new HttpGet(buf.toString());    //网络请求拼接好的地址
            //执行请求
            new DictAsycTask(monResponseListner).execute(request);            //异步任务执行网络请求
        }
    }

    class DictAsycTask extends AsyncTask<HttpUriRequest, Void, String> {

        onResponseListner monResponseListner;
        public DictAsycTask(onResponseListner m){
            monResponseListner = m;
        }
        int resultCode;
        @Override
        protected String doInBackground(HttpUriRequest... params) {
            //执行网络请求
            HttpClient client=new DefaultHttpClient();
            try {
                HttpResponse response=client.execute(params[0]);
                resultCode=response.getStatusLine().getStatusCode();
                if (resultCode == HttpStatus.SC_OK){  //获取响应码，响应码正确则读取响应的数据
                    //读取响应的数据
                    HttpEntity entity=response.getEntity();
                    return EntityUtils.toString(entity);
                }else {
                    params[0].abort(); //失败则停止
                }
            } catch (ClientProtocolException e){
                Log.e(TAG, "ClientProtocolException");
            } catch (IOException e) {
                Log.e(TAG, "IOException");
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {   //result是由DictAsycTask方法中return得到的
            super.onPostExecute(result);
            if (null!=result){
                //成功
                Log.e(TAG, "结果："+result);
                monResponseListner.onSuccess(result);
            }else {
                //失败
                //Log.e(TAG, "失败："+resultCode);
                monResponseListner.onFailed("error-->"+resultCode);
            }
        }
    }

    //回调借口
    public interface onResponseListner{
        void onSuccess(String result);
        void onFailed(String error);
    }
}
