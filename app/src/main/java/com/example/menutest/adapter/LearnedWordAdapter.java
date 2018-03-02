package com.example.menutest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.menutest.R;
import com.example.menutest.bean.LearnedWord;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/5.
 */

public class LearnedWordAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LearnedWord> data=new ArrayList<LearnedWord> ();

    public LearnedWordAdapter(Context context, ArrayList<LearnedWord> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = View.inflate(context, R.layout.list_demo, null);
        }

        LearnedWord learnedWord=data.get(position);
        TextView text1= (TextView) convertView.findViewById(R.id.list_text);
        text1.setText(learnedWord.getLearnedQuery());
        TextView text2= (TextView) convertView.findViewById(R.id.list_translation);
        text2.setText(learnedWord.getLearnedTranslation());
        return convertView;
    }
}
