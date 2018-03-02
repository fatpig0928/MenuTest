package com.example.menutest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        new Handler().postDelayed(new Runnable() {
            //为了减少代码使用匿名Handle创建一个延时的调用
            public void run() {
                Intent intent=new Intent(IndexActivity.this,MainActivity.class);
                //通过Intent打开最终真正的主界面main这个activity
                IndexActivity.this.startActivity(intent);
                IndexActivity.this.finish();
            }
        }, 2000);
    }
}

