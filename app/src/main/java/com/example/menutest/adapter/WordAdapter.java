package com.example.menutest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.menutest.R;
import com.example.menutest.bean.Word;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/4.
 */

public class WordAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Word> data=new ArrayList<Word>();

    public WordAdapter(Context context,ArrayList<Word> data){
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

        Word word=data.get(position);
        TextView text1= (TextView) convertView.findViewById(R.id.list_text);
        text1.setText(word.getQuery());
        TextView text2= (TextView) convertView.findViewById(R.id.list_translation);
        text2.setText(word.getTranslation());
        return convertView;
    }

}
