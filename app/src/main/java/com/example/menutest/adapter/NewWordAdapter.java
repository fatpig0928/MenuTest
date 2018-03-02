package com.example.menutest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.menutest.R;
import com.example.menutest.bean.NewWord;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/5.
 */

public class NewWordAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NewWord> data=new ArrayList<NewWord> ();

    public NewWordAdapter(Context context,ArrayList<NewWord> data){
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

        NewWord newWord=data.get(position);
        TextView text1= (TextView) convertView.findViewById(R.id.list_text);
        text1.setText(newWord.getNewQuery());
        TextView text2= (TextView) convertView.findViewById(R.id.list_translation);
        text2.setText(newWord.getNewTranslation());
        return convertView;
    }
}
