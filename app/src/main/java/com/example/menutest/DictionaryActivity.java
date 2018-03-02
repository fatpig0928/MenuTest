package com.example.menutest;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menutest.util.AsycHttp;
import com.example.menutest.util.Contants;
import com.example.menutest.util.Parser;

public class DictionaryActivity extends Activity {

    private TextView mTxtShowResult; //显示结果
    private EditText mEdtInput;       //输入关键字
    private TextView mTxtStartQuery; //手动执行查询

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        initView();


    }

    public void initView() {
        mTxtShowResult = (TextView) findViewById(R.id.text);
        //mImgVoiceQuery = (ImageView) findViewById(R.id.img_voice_query);
        mEdtInput = (EditText) findViewById(R.id.edit);
        mTxtStartQuery = (TextView) findViewById(R.id.search);

        mTxtStartQuery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //获取输入内容
                String input = mEdtInput.getText().toString().trim();
                //执行网络请求
                AsycHttp.getInstance().requestForGet(Contants.PATH_QUERY, input, monResponseListner);
            }
        });
    }

    //回调借口
    AsycHttp.onResponseListner monResponseListner = new AsycHttp.onResponseListner() {


        @Override
        public void onSuccess(String result) {
            //解析数据
             mTxtShowResult.setText(Parser.parserData(result).toString());
        }

        @Override
        public void onFailed(String error) {
            Toast.makeText(DictionaryActivity.this,"数据错误："+error,Toast.LENGTH_LONG).show();
        }
    };

}
