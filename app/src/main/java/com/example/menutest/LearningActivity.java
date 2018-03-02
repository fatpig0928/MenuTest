package com.example.menutest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menutest.bean.Word;
import com.example.menutest.database.DBHelper;
import com.example.menutest.database.WordDao;

import java.util.ArrayList;

public class LearningActivity extends Activity {

    private DBHelper dbHelper;
    private TextView query;
    private TextView phonetic;
    private TextView translation;
    private TextView example;
    private TextView mnemonic;
    private Button before_one;
    private Button next_one;
    private ImageButton add_new_word;
    private ImageView img_help;
    private ArrayList<Word> list = new ArrayList<Word>();
    SharedPreferences sp;
    int count;
    SQLiteDatabase database;
    String Word_ID = "wordId";
    WordDao dao=new WordDao(LearningActivity.this);
    Word word;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        init();
        testGetAll();
    }


    public void init(){

        dbHelper=new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        list = dao.getAll();
        query= (TextView) findViewById(R.id.query);
        phonetic= (TextView) findViewById(R.id.phonetic);
        translation= (TextView) findViewById(R.id.translation);
        example= (TextView) findViewById(R.id.example);
        mnemonic= (TextView) findViewById(R.id.mnemonic);

        img_help= (ImageView) findViewById(R.id.img_help);

        next_one = (Button) findViewById(R.id.next_one);
        before_one = (Button) findViewById(R.id.before_one);
        add_new_word= (ImageButton) findViewById(R.id.add_new_word);
        sp=getSharedPreferences("learn_sys",MODE_PRIVATE);
        count=sp.getInt("count",0);   //第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        query.setText(list.get(count).getQuery());
        filename=list.get(count).getQuery()+".png";
        read(filename);
        phonetic.setText(list.get(count).getUkPhonetic());
        translation.setText(list.get(count).getTranslation());
        example.setText(list.get(count).getExample());
        mnemonic.setText(list.get(count).getMnemonic());



        /**
         * 确定的监听设置
         */

        next_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if (count==322) {
                            Dialog dialog = new AlertDialog.Builder(LearningActivity.this)
                                    .setTitle("恭喜你！")
                                    .setMessage("你已经学完了全部单词，是否重新学习")
                                    .setIcon(R.drawable.congratulation)
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            count=0;
                                            query.setText(list.get(count).getQuery());
                                            filename = list.get(count).getQuery() + ".png";
                                            read(filename);
                                            phonetic.setText(list.get(count).getUkPhonetic());
                                            translation.setText(list.get(count).getTranslation());
                                            example.setText(list.get(count).getExample());
                                            mnemonic.setText(list.get(count).getMnemonic());
                                        }
                                    })
                                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            AgentApp.getInstangce().onTerminate();    //退出
                                        }
                                    }).create();
                            dialog.show();
                        }else {
                            word = list.get(count);
                            updatemark(word);
                            dao=new WordDao(LearningActivity.this);
                            dao.addLearned(list.get(count).getWordId()
                                    , list.get(count).getQuery()
                                    , list.get(count).getUkPhonetic()
                                    , list.get(count).getTranslation()
                                    , list.get(count).getExample());
                            count++;
                            query.setText(list.get(count).getQuery());
                            filename = list.get(count).getQuery() + ".png";
                            read(filename);
                            phonetic.setText(list.get(count).getUkPhonetic());
                            translation.setText(list.get(count).getTranslation());
                            example.setText(list.get(count).getExample());
                            mnemonic.setText(list.get(count).getMnemonic());
                            sp = getSharedPreferences("learn_sys", Activity.MODE_WORLD_WRITEABLE);
                            SharedPreferences.Editor ed = sp.edit();
                            ed.putInt("count", count);
                            ed.commit();//提交数据
                        }
            }
        });

        before_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (count!=0){
                    count--;
                    query.setText(list.get(count).getQuery());
                    filename=list.get(count).getQuery()+".png";
                    read(filename);
                    phonetic.setText(list.get(count).getUkPhonetic());
                    translation.setText(list.get(count).getTranslation());
                    example.setText(list.get(count).getExample());
                    mnemonic.setText(list.get(count).getMnemonic());
                    sp = getSharedPreferences("learn_sys", Activity.MODE_WORLD_WRITEABLE);
                    SharedPreferences.Editor ed =sp.edit();
                    ed.putInt("count", count);
                    ed.commit();//提交数据
                }else count=1;



            }
        });

        add_new_word.setOnClickListener(new View.OnClickListener() {
            //count++;    //得到当前页面的count
            @Override
            public void onClick(View arg0) {
                Dialog dialog = new AlertDialog.Builder(LearningActivity.this)
                        .setTitle("添加生词")
                        .setMessage("确定要将该单词添加入生词本吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                sp=getSharedPreferences("Learn_sys",MODE_PRIVATE);
                                count=sp.getInt("count",count);
                                dao=new WordDao(LearningActivity.this);
                                dao.addNew(list.get(count).getWordId()
                                        , list.get(count).getQuery()
                                        , list.get(count).getUkPhonetic()
                                        , list.get(count).getTranslation()
                                        , list.get(count).getExample());

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        }).create();
                dialog.show();

            }
        });

    }    //init()方法结束

    private void updatemark(Word word) {
        String mark="2000";   //word应该是传过来的word值
        word.setMark(mark);
        dao.update(word);
    }

    public void testGetAll(){
        dao=new WordDao(this);
        ArrayList<Word> list=dao.getAll();
        Log.i("LearningActivity",list.toString());
    }

    public void read(String filename) {// /data/data/packageName/files/logo.png
        //1. 得到图片文件的路径
        // /data/data/packageName/files
        String filesPath = getFilesDir().getAbsolutePath();
        String imagePath = filesPath+"/"+filename;
        //2. 读取加载图片文件得到bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //3. 将其设置到imageView中显示
        img_help.setImageBitmap(bitmap);
    }


}

