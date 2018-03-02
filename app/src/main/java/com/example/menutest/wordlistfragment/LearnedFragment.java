package com.example.menutest.wordlistfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.menutest.R;
import com.example.menutest.adapter.LearnedWordAdapter;
import com.example.menutest.bean.LearnedWord;
import com.example.menutest.database.WordDao;

import java.util.ArrayList;


public class LearnedFragment extends Fragment {
    private ListView word_List;
    private LearnedWordAdapter adapter;
    private ArrayList<LearnedWord> data;
    private WordDao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.word_list_demo, container, false);

        word_List= (ListView) view.findViewById(R.id.word_List);
        dao=new WordDao(getContext());
        data=dao.getLearnedWord();
        adapter=new LearnedWordAdapter(getContext(),data);
        word_List.setAdapter(adapter);

        return view;
    }


}
