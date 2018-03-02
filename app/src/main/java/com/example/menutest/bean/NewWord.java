package com.example.menutest.bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class NewWord {

    private String newWordId;
    private String newQuery;
    private String newUkPhonetic;
    private String newTranslation;
    private String newExample;

    public NewWord(String newWordId, String newQuery, String newUkPhonetic, String newTranslation, String newExample) {
        this.newWordId = newWordId;
        this.newQuery = newQuery;
        this.newUkPhonetic = newUkPhonetic;
        this.newTranslation = newTranslation;
        this.newExample = newExample;
    }

    public String getNewWordId() {
        return newWordId;
    }

    public void setNewWordId(String newWordId) {
        this.newWordId = newWordId;
    }

    public String getNewQuery() {
        return newQuery;
    }

    public void setNewQuery(String newQuery) {
        this.newQuery = newQuery;
    }

    public String getNewUkPhonetic() {
        return newUkPhonetic;
    }

    public void setNewUkPhonetic(String newUkPhonetic) {
        this.newUkPhonetic = newUkPhonetic;
    }

    public String getNewTranslation() {
        return newTranslation;
    }

    public void setNewTranslation(String newTranslation) {
        this.newTranslation = newTranslation;
    }

    public String getNewExample() {
        return newExample;
    }

    public void setNewExample(String newExample) {
        this.newExample = newExample;
    }


    @Override
    public String toString() {
        return "NewWord{" +
                "newWordId='" + newWordId + '\'' +
                ", newQuery='" + newQuery + '\'' +
                ", newUkPhonetic='" + newUkPhonetic + '\'' +
                ", newTranslation='" + newTranslation + '\'' +
                ", newExample='" + newExample + '\'' +
                '}';
    }
}
