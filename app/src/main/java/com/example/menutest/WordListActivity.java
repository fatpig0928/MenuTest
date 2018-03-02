package com.example.menutest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.menutest.wordlistfragment.AllWordFragment;
import com.example.menutest.wordlistfragment.LearnedFragment;
import com.example.menutest.wordlistfragment.UnlearnFragment;

public class WordListActivity extends FragmentActivity {

    private RelativeLayout RelativeLayout1, RelativeLayout2, RelativeLayout3;
    private TextView textView1, textView2, textView3;
    private ViewPager viewPager;
    private int[] selectList;
    private TextView[] textViewList;
    private int selectID = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        initLayout();
        initData();
    }

    private void initLayout() {
        RelativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        RelativeLayout2 = (RelativeLayout) findViewById(R.id.RelativeLayout2);
        RelativeLayout3 = (RelativeLayout) findViewById(R.id.RelativeLayout3);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initData() {
        selectList = new int[] { 0, 1, 1};// 0表示选中，1表示未选中(默认第一个选中)
        textViewList = new TextView[] { textView1, textView2, textView3};

        RelativeLayout1.setOnClickListener(listener);
        RelativeLayout2.setOnClickListener(listener);
        RelativeLayout3.setOnClickListener(listener);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(changeListener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.RelativeLayout1:
                    if (selectID == 0) {
                        return;
                    } else {
                        setSelectedTitle(0);
                        viewPager.setCurrentItem(0);
                    }
                    break;
                case R.id.RelativeLayout2:
                    if (selectID == 1) {
                        return;
                    } else {
                        setSelectedTitle(1);
                        viewPager.setCurrentItem(1);
                    }
                    break;
                case R.id.RelativeLayout3:
                    if (selectID == 2) {
                        return;
                    } else {
                        setSelectedTitle(2);
                        viewPager.setCurrentItem(2);
                    }
                    break;
            }
        }
    };



    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(
            getSupportFragmentManager()) {

        public int getCount() {
            return selectList.length;
        }

        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment=new AllWordFragment();
                    break;
                case 1:
                    fragment=new LearnedFragment();
                    break;
                case 2:
                    fragment=new UnlearnFragment();
                    break;
            }
            return fragment;
        }
    };

    private ViewPager.SimpleOnPageChangeListener changeListener=new ViewPager.SimpleOnPageChangeListener(){
        public void onPageSelected(int position) {
            setSelectedTitle(position);
        }
    };

    /**
     * 当前UI改变时，修改TITLE选中项
     *
     * @param position
     *            0 1 2
     */
    private void setSelectedTitle(int position) {
        for (int i = 0; i < selectList.length; i++) {
            if (selectList[i] == 0) {
                selectList[i] = 1;
                textViewList[i].setVisibility(View.INVISIBLE);
            }
        }
        selectList[position] = 0;
        textViewList[position].setVisibility(View.VISIBLE);
        selectID = position;
    }
}
