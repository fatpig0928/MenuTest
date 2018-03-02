package com.example.menutest.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class LearnedWord {

    private String learnedWordId;
    private String learnedQuery;
    private String learnedUkPhonetic;
    private String learnedTranslation;
    private String learnedExample;

    public LearnedWord(String learnedWordId, String learnedQuery, String learnedUkPhonetic, String learnedTranslation, String learnedExample) {
        this.learnedWordId = learnedWordId;
        this.learnedQuery = learnedQuery;
        this.learnedUkPhonetic = learnedUkPhonetic;
        this.learnedTranslation = learnedTranslation;
        this.learnedExample = learnedExample;
    }

    public String getLearnedWordId() {
        return learnedWordId;
    }

    public void setLearnedWordId(String learnedWordId) {
        this.learnedWordId = learnedWordId;
    }

    public String getLearnedQuery() {
        return learnedQuery;
    }

    public void setLearnedQuery(String learnedQuery) {
        this.learnedQuery = learnedQuery;
    }

    public String getLearnedUkPhonetic() {
        return learnedUkPhonetic;
    }

    public void setLearnedUkPhonetic(String learnedUkPhonetic) {
        this.learnedUkPhonetic = learnedUkPhonetic;
    }

    public String getLearnedTranslation() {
        return learnedTranslation;
    }

    public void setLearnedTranslation(String learnedTranslation) {
        this.learnedTranslation = learnedTranslation;
    }

    public String getLearnedExample() {
        return learnedExample;
    }

    public void setLearnedExample(String learnedExample) {
        this.learnedExample = learnedExample;
    }

    @Override
    public String toString() {
        return "LearnedWord{" +
                "learnedWordId='" + learnedWordId + '\'' +
                ", learnedQuery='" + learnedQuery + '\'' +
                ", learnedUkPhonetic='" + learnedUkPhonetic + '\'' +
                ", learnedTranslation='" + learnedTranslation + '\'' +
                ", learnedExample='" + learnedExample + '\'' +
                '}';
    }
}
