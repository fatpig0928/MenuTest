package com.example.menutest.order;

import com.example.menutest.bean.Word;

import java.util.Comparator;

/**
 *
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<Word> {

    public int compare(Word o1, Word o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }

}