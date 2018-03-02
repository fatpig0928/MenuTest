package com.example.menutest.wordlistfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.menutest.R;
import com.example.menutest.bean.Word;
import com.example.menutest.database.WordDao;
import com.example.menutest.order.ClearEditText;
import com.example.menutest.order.PinyinComparator;
import com.example.menutest.order.SideBar;
import com.example.menutest.order.SortAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AllWordFragment extends Fragment {

    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;

    /**
     * 汉字转换成拼音的类
     */
    private List<Word> SourceDateList;


    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_all_word, container, false);


        //实例化汉字转拼音类
        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) view.findViewById(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getContext(), ((Word)adapter.getItem(position)).getQuery(), Toast.LENGTH_SHORT).show();
            }
        });

        SourceDateList = filledData();

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(getContext(), SourceDateList);
        sortListView.setAdapter(adapter);


        mClearEditText = (ClearEditText) view.findViewById(R.id.filter_edit);

        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    /**
     * 为ListView填充数据
     * @return
     */
    private List<Word> filledData(){
        WordDao dao=new WordDao(getContext());
        List<Word> mSortList=new ArrayList<>();
        List<Word> list=dao.getAll();
        for(int i=0; i<list.size(); i++){
            Word word = new Word();
            word.setQuery(list.get(i).getQuery());
            word.setTranslation(list.get(i).getTranslation());
            //汉字转换成拼音
            String pinyin =list.get(i).getQuery();
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                word.setSortLetters(sortString.toUpperCase());
            }else{
                word.setSortLetters("#");
            }

            mSortList.add(word);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<Word> filterDateList = new ArrayList<Word>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(Word word : SourceDateList){
                String query = word.getQuery();
                if(query.indexOf(filterStr.toString()) != -1 ){
                    filterDateList.add(word);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
