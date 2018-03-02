package com.example.menutest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.menutest.bean.LearnedWord;
import com.example.menutest.bean.Plan;
import com.example.menutest.database.WordDao;
import com.example.menutest.view.SlidingMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FragmentHome extends Fragment{

    private Button frg_home_dictionary;
    private Button fra_home_word_list=null;
    private Button fra_home_learn=null;
    private SlidingMenu slidingMenu;
    private TextView progress_tv,countdown_days;
    private SpringProgressView progressView;
    private ArrayList<LearnedWord> list;
    private ArrayList<Plan> planList = new ArrayList<Plan>();
    WordDao dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_home, container, false);

        dao=new WordDao(getContext());


        frg_home_dictionary= (Button) rootView.findViewById(R.id.frg_home_dictionary);
        frg_home_dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity()
                        .getApplicationContext(), DictionaryActivity.class);
                startActivity(intent);
            }
        });

        fra_home_word_list= (Button) rootView.findViewById(R.id.fra_home_word_list);
        fra_home_word_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity()
                        .getApplicationContext(), WordListActivity.class);
                startActivity(intent);
            }
        });

        fra_home_learn= (Button) rootView.findViewById(R.id.fra_home_learn);
        fra_home_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity()
                        .getApplicationContext(), LearningActivity.class);
                startActivity(intent);
            }
        });


        //slidingMenu = (SlidingMenu) rootView.findViewById(R.id.mLeftMenu);
        //slidingMenu.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
               //slidingMenu.toggle();
            //}
        //});

        progress_tv = (TextView) rootView.findViewById(R.id.progress_tv);
        progressView = (SpringProgressView) rootView.findViewById(R.id.spring_progress_view);
        countdown_days= (TextView) rootView.findViewById(R.id.countdown_days);

        return rootView;


    }

    @Override
    public void onResume() {
        super.onResume();

        list=dao.getLearnedWord();

        progressView.setMaxCount(322.0f);
        progressView.setCurrentCount(list.size());
        progress_tv.setText("已完成"+list.size()+"/322");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            String edate;
            planList=dao.getPlan();
            if (planList.size()<=1) countdown_days.setText("0");
            else {
                edate = planList.get(1).getYear() + "-" + (planList.get(1).getMonth() + 1) + "-" + planList.get(1).getDay();
                Date sd = new Date(System.currentTimeMillis());
                Date ed = sdf.parse(edate);
                int dayCount = (int) ((ed.getTime() - sd.getTime()) / 86400000);
                countdown_days.setText( ""+ (dayCount+1));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
