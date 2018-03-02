package com.example.menutest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.menutest.adapter.NewWordAdapter;
import com.example.menutest.bean.NewWord;
import com.example.menutest.database.WordDao;

import java.util.ArrayList;

public class FragmentDown extends Fragment {

    private ListView word_List;
    private NewWordAdapter adapter;
    private ArrayList<NewWord> data;
    private WordDao dao;
    private int position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.word_list_demo, container, false);

        word_List= (ListView) view.findViewById(R.id.word_List);
        dao=new WordDao(getContext());
        data=dao.getAllNewWord();
        adapter=new NewWordAdapter(getContext(),data);
        word_List.setAdapter(adapter);

        //给listView设置创建ContextMenu的监听
        word_List.setOnCreateContextMenuListener(this);

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //添加2个item
        menu.add(0, 1, 0, "更新");
        menu.add(0, 2, 0, "删除");

        //得到长按的position
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //得到对应的BlackNumber对象
        NewWord newWord = data.get(position);
        switch (item.getItemId()) {
            case 1://更新
                //1. 显示更新的Dialog
                showUpdateDialog(newWord);
                break;
            case 2://删除
                //1). 删除数据表对应的数据
                dao.deleteNewWordById(newWord.getNewWordId());
                //2). 删除List对应的数据
                data.remove(position);
                //3). 通知更新列表
                adapter.notifyDataSetChanged();
                break;

            default:
                break;
        }


        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final NewWord newWord) {
        final EditText editText = new EditText(getContext());
        editText.setText(newWord.getNewTranslation());
        new AlertDialog.Builder(getContext())
                .setTitle("更新生词本")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //1). 更新List对应的数据
                        String newTranslation = editText.getText().toString();
                        newWord.setNewTranslation(newTranslation);

                        //2). 更新数据表对应的数据
                        dao.update(newWord);

                        //3). 通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

}
