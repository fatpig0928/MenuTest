package com.example.menutest.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class Word {

    private String wordId;
    private String query;
    private String ukPhonetic;
    private String translation;
    private String example;
    private String mnemonic;
    private String mark;
    private String sortLetters;

    public Word(String wordId, String query, String ukPhonetic, String translation, String example, String mnemonic, String mark) {
        this.wordId = wordId;
        this.query = query;
        this.ukPhonetic = ukPhonetic;
        this.translation = translation;
        this.example = example;
        this.mnemonic = mnemonic;
        this.mark = mark;
    }

    public Word() {

    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUkPhonetic() {
        return ukPhonetic;
    }

    public void setUkPhonetic(String ukPhonetic) {
        this.ukPhonetic = ukPhonetic;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId='" + wordId + '\'' +
                ", query='" + query + '\'' +
                ", ukPhonetic='" + ukPhonetic + '\'' +
                ", translation='" + translation + '\'' +
                ", example='" + example + '\'' +
                ", mnemonic='" + mnemonic + '\'' +
                ", mark='" + mark + '\'' +
                ", sortLetters='" + sortLetters + '\'' +
                '}';
    }
}
