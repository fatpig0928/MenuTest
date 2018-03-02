package com.example.menutest;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.menutest.bean.LearnedWord;
import com.example.menutest.database.DBHelper;
import com.example.menutest.database.WordDao;

import java.util.ArrayList;

public class ReviewAllSpellActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private TextView input;
    private TextView test_jilu;
    private TextView test_hint;
    private TextView test_mean;
    private Button test_ok;
    private Button test_delete;
    SharedPreferences sp;
    StringBuilder sb = new StringBuilder();
    ImageButton bna, bnb, bnc, bnd, bne, bnf, bng, bnh, bni, bnj, bnk, bnl,
            bnm, bnn, bno, bnp, bnq, bnr, bns, bnt, bnu, bnv, bnw, bnx, bny,
            bnz;
    Cursor cursor;
    int count;
    SQLiteDatabase database;
    String Word_ID = "learnedWordId";
    WordDao dao=new WordDao(ReviewAllSpellActivity.this);
    private ArrayList<LearnedWord> list = new ArrayList<LearnedWord>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_review_all_spell);


        init();
    }

    public void init(){

        dbHelper=new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        input = (TextView) findViewById(R.id.test_put);
        test_jilu= (TextView) findViewById(R.id.test_jilu);
        test_mean= (TextView) findViewById(R.id.test_mean);
        test_hint= (TextView) findViewById(R.id.test_hint);
        test_delete= (Button) findViewById(R.id.test_delete);
        test_ok = (Button) findViewById(R.id.test_ok);
        sp=getSharedPreferences("sys",MODE_PRIVATE);
        count=sp.getInt("count",0);
        list=dao.getLearnedWord();
        if(list.size()==0){
            Dialog dialog = new android.app.AlertDialog.Builder(ReviewAllSpellActivity.this)
                    .setTitle("还没有学习单词哟！")
                    .setMessage("是否开始学习？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            Intent intent = new Intent(ReviewAllSpellActivity.this, LearningActivity.class);
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(ReviewAllSpellActivity.this, FragmentHome.class);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();

        }else {
            test_mean.setText(list.get(count).getLearnedTranslation());
            test_hint.setText("提示：" + list.get(count).getLearnedQuery().length() + "个字母");
        }

        bna = (ImageButton) findViewById(R.id.a);
        bna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("a"));
            }
        });

        bnb = (ImageButton) findViewById(R.id.b);
        bnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("b"));
            }
        });

        bnc = (ImageButton) findViewById(R.id.c);
        bnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("c"));
            }
        });

        bnd = (ImageButton) findViewById(R.id.d);
        bnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("d"));
            }
        });

        bne = (ImageButton) findViewById(R.id.e);
        bne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("e"));
            }
        });

        bnf = (ImageButton) findViewById(R.id.f);
        bnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("f"));
            }
        });

        bng = (ImageButton) findViewById(R.id.g);
        bng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("g"));
            }
        });

        bnh = (ImageButton) findViewById(R.id.h);
        bnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("h"));
            }
        });

        bni = (ImageButton) findViewById(R.id.i);
        bni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("i"));
            }
        });

        bnj = (ImageButton) findViewById(R.id.j);
        bnj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("j"));
            }
        });

        bnk = (ImageButton) findViewById(R.id.k);
        bnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("k"));
            }
        });

        bnl = (ImageButton) findViewById(R.id.l);
        bnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("l"));
            }
        });

        bnm = (ImageButton) findViewById(R.id.m);
        bnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("m"));
            }
        });

        bnn = (ImageButton) findViewById(R.id.n);
        bnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("n"));
            }
        });

        bno = (ImageButton) findViewById(R.id.o);
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("o"));
            }
        });

        bnp = (ImageButton) findViewById(R.id.p);
        bnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("p"));
            }
        });

        bnq = (ImageButton) findViewById(R.id.q);
        bnq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("q"));
            }
        });

        bnr = (ImageButton) findViewById(R.id.r);
        bnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("r"));
            }
        });

        bns = (ImageButton) findViewById(R.id.s);
        bns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("s"));
            }
        });

        bnt = (ImageButton) findViewById(R.id.t);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("t"));
            }
        });

        bnu = (ImageButton) findViewById(R.id.u);
        bnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("u"));
            }
        });

        bnv = (ImageButton) findViewById(R.id.v);
        bnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("v"));
            }
        });

        bnw = (ImageButton) findViewById(R.id.w);
        bnw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("w"));
            }
        });


        bnx = (ImageButton) findViewById(R.id.x);
        bnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("x"));
            }
        });

        bny = (ImageButton) findViewById(R.id.y);
        bny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("y"));
            }
        });

        bnz = (ImageButton) findViewById(R.id.z);
        bnz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(sb.append("z"));
            }
        });

        /**
         * 确定的监听设置
         */
        test_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String aString = input.getText().toString();
                String bString =list.get(count).getLearnedQuery();
                if (aString.equals(bString)) {
                    openOptionsDialog(1);
                    test_jilu.setText("答对  "+(count+1)+" 个");
                } else
                    openOptionsDialog(2);
            }
        });

        /**
         * 删除的设置
         */
        test_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                char[] q = {};
                q = input.getText().toString().toCharArray();
                int a = 0;
                a = input.getText().length();
                if (a >= 2) {
                    sb = new StringBuilder();
                    int i = 0;
                    for (i = 0; i <= a - 2; i++) {
                        input.setText(sb.append(q[i]));
                    }
                } else {
                    input.setText("");
                    sb = new StringBuilder();
                }
            }
        });
    }    //init()方法结束


    private void openOptionsDialog(int i) {
        if (i == 1) {
            new AlertDialog.Builder(this)
                    .setTitle(" 你真棒！" + "" + "继续加油哦！")
                    .setIcon(R.drawable.good)
                    .setPositiveButton("下一个",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int i) {
                                    sb = new StringBuilder();
                                    input.setText("");
                                    count++;
                                    test_mean.setText(list.get(count).getLearnedTranslation());    //getColumnIndex，返回指定列的名称，如果不存在返回-1
                                    test_hint.setText("提示：" + list.get(count).getLearnedQuery().length() + "个字母");
                                }
                            }).show();

        }
        if (i == 2) {
            new AlertDialog.Builder(this)
                    .setTitle("亲，错咯，请继续加油！")
                    .setIcon(R.drawable.bad)
                    .setPositiveButton("重新输入",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int i) {
                                }
                            }).show();
        }
    }

    /**
     * 顶部菜单栏
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        SubMenu sub = menu.addSubMenu(0,1,1,"结束");
        sub.add(1,2,1,"保存当前进度");    //1为GroupId,2为ItemId,第三个是Item的顺序
        sub.add(1,3,2,"直接退出");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 2:
                sp = getSharedPreferences("sys", Activity.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor ed =sp.edit();
                ed.putInt("count", count);
                ed.commit();//提交数据
                AgentApp.getInstangce().onTerminate();    //退出
                break;
            case 3:
                AgentApp.getInstangce().onTerminate();
                break;
        }
        return true;
    }

}

