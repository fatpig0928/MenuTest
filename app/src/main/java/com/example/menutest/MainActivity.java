package com.example.menutest;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menutest.addword.AddWord;
import com.example.menutest.bean.Word;
import com.example.menutest.database.WordDao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @功能说明 自定义TabHost
 */
public class MainActivity extends FragmentActivity {

    //private SlidingMenu mLeftMenu ;
    WordDao dao=new WordDao(this);
    String query;
    String filename;
    private ArrayList<Word> list = new ArrayList<Word>();
    AddWord addWord=new AddWord();

    private Button left_menu_1;
    private Button left_menu_help;
    private Button left_menu_feedback;

    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    // 定义一个布局
    private LayoutInflater layoutInflater;

    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = { FragmentHome.class,
            FragmentDown.class, FragmentUser.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = { R.drawable.main_tab_item_home,
            R.drawable.main_tab_item_down, R.drawable.main_tab_item_user};

    // Tab选项卡的文字
    private String mTextviewArray[] = { "主页", "生词本", "复习"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addWord.testAdd();
        addFunPhoto();

        //mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);


    }

    /**
     * <底部菜单栏>初始化组件
     */
    private void initView() {

        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        left_menu_1= (Button) findViewById(R.id.left_menu_1);
        left_menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Left_plan.class);
                startActivity(intent);
            }
        });

        left_menu_help= (Button) findViewById(R.id.left_menu_help);
        left_menu_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeftHelp.class);
                startActivity(intent);
            }
        });

        left_menu_feedback= (Button) findViewById(R.id.left_menu_feedback);
        left_menu_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeftFeedback.class);
                startActivity(intent);
            }
        });

        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);  //去除FragmentTabHost的分割线

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.main_tab_item_bg);
        }
    }

    /**
     * <底部菜单栏>给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    /**
	 * <点击两次退出>
	 */
    private boolean exit=false;

    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if (msg.what==1) {
                exit=false;
            }
        }
    };
    public boolean onKeyUp(int keyCode,KeyEvent event){

        if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
            if (!exit) {
                exit=true;
                Toast.makeText(this, "再点一次退出",Toast.LENGTH_LONG).show();
                //发消息延迟2s将exit赋为false
                handler.sendEmptyMessageDelayed(1, 2000);
                return true; //不退出
            }

        }

        return super.onKeyUp(keyCode, event);
    }

    /**
     *<侧滑>
     */
    //public void toggleMenu(View view)
    //{
       // mLeftMenu.toggle();
   // }


    /**
     * 添加趣味图
     */
    public void addFunPhoto(){
        try {
            list = dao.getAll();
            for (int count=0; count<list.size(); count++){
                query=list.get(count).getQuery();
                filename=query+".png";
                save(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String filename) throws IOException {
        //1. 得到InputStream-->读取assets下的logo.png
        //得到AssetManager
        AssetManager manager = getAssets();
        //读取文件
        InputStream is = manager.open(filename);
        FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=is.read(buffer))!=-1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        is.close();
    }


}

