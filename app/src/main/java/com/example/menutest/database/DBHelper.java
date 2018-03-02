package com.example.menutest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String wordListTable = "wordListTable";
    public static final String wordId = "wordId";
    public static final String query = "query";
    public static final String ukPhonetic = "ukPhonetic";
    public static final String translation = "translation";
    public static final String example = "example";
    public static final String mnemonic = "mnemonic";
    public static final String mark = "mark";


    public static final String newWordListTable = "newWordListTable";
    public static final String newWordId = "newWordId";
    public static final String newQuery = "newQuery";
    public static final String newUkPhonetic = "newUkPhonetic";
    public static final String newTranslation = "newTranslation";
    public static final String newExample = "newExample";

    public static final String learnedListTable = "learnedListTable";
    public static final String learnedWordId = "learnedWordId";
    public static final String learnedQuery = "learnedQuery";
    public static final String learnedUkPhonetic = "learnedUkPhonetic";
    public static final String learnedTranslation = "learnedTranslation";
    public static final String learnedExample = "learnedExample";

    public static final String planTable = "planTable";
    public static final String planId = "planId";
    public static final String year = "year";
    public static final String month = "month";
    public static final String day = "day";


    public DBHelper(Context context) {
        super(context, "wordList.db", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + wordListTable + " ("
                + wordId + " INTEGER PRIMARY KEY,"
                + query + " VARCHAR,"
                + ukPhonetic + " VARCHAR,"
                + translation + " VARCHAR,"
                + example + " VARCHAR,"
                + mnemonic + " VARCHAR,"
                + mark + " VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + newWordListTable + " ("
                + newWordId + " INTEGER PRIMARY KEY,"
                + newQuery + " VARCHAR,"
                + newUkPhonetic + " VARCHAR,"
                + newTranslation + " VARCHAR,"
                + newExample + " VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + learnedListTable + " ("
                + learnedWordId + " INTEGER PRIMARY KEY,"
                + learnedQuery + " VARCHAR,"
                + learnedUkPhonetic + " VARCHAR,"
                + learnedTranslation + " VARCHAR,"
                + learnedExample + " VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + planTable + " ("
                + planId + " INTEGER PRIMARY KEY,"
                + year + " INTEGER,"
                + month + " INTEGER,"
                + day + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
