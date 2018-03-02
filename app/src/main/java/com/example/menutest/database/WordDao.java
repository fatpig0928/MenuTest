package com.example.menutest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.menutest.bean.LearnedWord;
import com.example.menutest.bean.NewWord;
import com.example.menutest.bean.Plan;
import com.example.menutest.bean.Word;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/20.
 */

public class WordDao {

    private DBHelper dbHelper;
    String Mark = "mark";

    public WordDao(Context context){
        dbHelper=new DBHelper(context);
    }



    /**
     * 插入全部单词
     * @param word
     */
    public void add(Word word){
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("wordId",word.getWordId());
        values.put("query",word.getQuery());
        values.put("ukPhonetic",word.getUkPhonetic());
        values.put("translation",word.getTranslation());
        values.put("example",word.getExample());
        values.put("mnemonic",word.getMnemonic());
        values.put("mark",word.getMark());
        long id=database.insert("wordListTable",null,values);
        Log.e("TAG","id="+id);
        database.close();
    }



    /**
     * 更新全部单词
     * @param word
     */
    public void update(Word word) {
        //1. 得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //2. 执行update update black_number set newTranslation=xxx where newWordId=id
        ContentValues values = new ContentValues();
        values.put("mark", word.getMark());
        int updateCount = database.update("wordListTable", values , "wordId="+word.getWordId(), null);
        Log.i("TAG", "updateCount="+updateCount);
        //3. 关闭
        database.close();
    }

    /**
     * 查询全部单词
     * @return
     */
    public ArrayList<Word> getAll(){

        ArrayList<Word> list=new ArrayList<Word>();
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query("wordListTable",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String wordId=cursor.getString(0);
            String query=cursor.getString(1);
            String ukPhonetic=cursor.getString(2);
            String translation=cursor.getString(3);
            String example=cursor.getString(4);
            String mnemonic=cursor.getString(5);
            String mark=cursor.getString(6);
            list.add(new Word(wordId,query,ukPhonetic,translation,example,mnemonic,mark));
        }
        cursor.close();
        database.close();

        return list;
    }

    /**
     * 添加已学单词
     */
    public void addLearned(String learnedWordId,String learnedQuery,String learnedUkPhonetic,String learnedTranslation,String learnedExample){
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(dbHelper.learnedWordId,learnedWordId);
        values.put(dbHelper.learnedQuery,learnedQuery);
        values.put(dbHelper.learnedUkPhonetic,learnedUkPhonetic);
        values.put(dbHelper.learnedTranslation,learnedTranslation);
        values.put(dbHelper.learnedExample,learnedExample);
        long id=database.insert("learnedListTable",null,values);
        Log.e("TAG","id="+id);
        database.close();
    }

    /**
     * 查询已学单词
     * @return
     */
    public ArrayList<LearnedWord> getLearnedWord(){

        ArrayList<LearnedWord> list=new ArrayList<LearnedWord>();
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query("learnedListTable",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String wordId=cursor.getString(0);
            String query=cursor.getString(1);
            String ukPhonetic=cursor.getString(2);
            String translation=cursor.getString(3);
            String example=cursor.getString(4);
            list.add(new LearnedWord(wordId,query,ukPhonetic,translation,example));
        }
        cursor.close();
        database.close();

        return list;
    }




    /**
     * 查询未学单词
     * @return
     */
    public ArrayList<Word> getUnlearnWord(){

        ArrayList<Word> list=new ArrayList<Word>();
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query("wordListTable",null,Mark + "=" + "0",null,null,null,null);
        while (cursor.moveToNext()){
            String wordId=cursor.getString(0);
            String query=cursor.getString(1);
            String ukPhonetic=cursor.getString(2);
            String translation=cursor.getString(3);
            String example=cursor.getString(4);
            String mnemonic=cursor.getString(5);
            String mark=cursor.getString(6);
            list.add(new Word(wordId,query,ukPhonetic,translation,example,mnemonic,mark));
        }
        cursor.close();
        database.close();

        return list;
    }

    /**
     * 添加生词本
     */
    public void addNew(String newWordId,String newQuery,String newUkPhonetic,String newTranslation,String newExample){
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(dbHelper.newWordId,newWordId);
        values.put(dbHelper.newQuery,newQuery);
        values.put(dbHelper.newUkPhonetic,newUkPhonetic);
        values.put(dbHelper.newTranslation,newTranslation);
        values.put(dbHelper.newExample,newExample);
        long id=database.insert("newWordListTable",null,values);
        Log.e("TAG","id="+id);
        database.close();
    }

    /**
     * 删除生词
     */
    public void deleteNewWordById(String newWordId){
        //1. 得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //2. 执行delete delete from black_number where _id=id
        int deleteCount = database.delete("newWordListTable", "newWordId=?", new String[]{newWordId+""});
        Log.i("TAG", "deleteCount="+deleteCount);
        //3. 关闭
        database.close();
    }

    /**
     * 更新生词
     */
    public void update(NewWord newWord) {
        //1. 得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //2. 执行update update black_number set newTranslation=xxx where newWordId=id
        ContentValues values = new ContentValues();
        values.put("newTranslation", newWord.getNewTranslation());
        int updateCount = database.update("newWordListTable", values , "newWordId="+newWord.getNewWordId(), null);
        Log.i("TAG", "updateCount="+updateCount);
        //3. 关闭
        database.close();
    }

    /**
     * 查询生词本
     * @return
     */
    public ArrayList<NewWord> getAllNewWord(){

        ArrayList<NewWord> list=new ArrayList<NewWord>();
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query("newWordListTable",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String newWordId=cursor.getString(0);
            String newQuery=cursor.getString(1);
            String newUkPhonetic=cursor.getString(2);
            String newTranslation=cursor.getString(3);
            String newExample=cursor.getString(4);
            list.add(new NewWord(newWordId,newQuery,newUkPhonetic,newTranslation,newExample));
        }
        cursor.close();
        database.close();

        return list;
    }


    /**
     * 查询计划
     * @return
     */
    public ArrayList<Plan> getPlan(){

        ArrayList<Plan> list=new ArrayList<Plan>();
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        Cursor cursor=database.query("planTable",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int planId=cursor.getInt(0);
            int year=cursor.getInt(1);
            int month=cursor.getInt(2);
            int day=cursor.getInt(3);
            list.add(new Plan(planId,year,month,day));
        }
        cursor.close();
        database.close();

        return list;
    }

    /**
     * 添加计划
     */
    public void addPlan(int planId,int year,int month,int day){
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(dbHelper.planId,planId);
        values.put(dbHelper.year,year);
        values.put(dbHelper.month,month);
        values.put(dbHelper.day,day);
        long id=database.insert("planTable",null,values);
        database.close();
    }

    /**
     * 更新生词
     */
    public void update(Plan plan) {
        //1. 得到连接
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        //2. 执行update update black_number set newTranslation=xxx where newWordId=id
        ContentValues values = new ContentValues();
        values.put("year", plan.getYear());
        values.put("month", plan.getMonth());
        values.put("day", plan.getDay());
        int updateCount = database.update("planTable", values , "planId="+plan.getPlanId(), null);
        Log.i("TAG", "updateCount="+updateCount);
        //3. 关闭
        database.close();
    }

}
