package com.example.menutest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.menutest.bean.Plan;
import com.example.menutest.database.WordDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时间选择器
 */
public class Left_plan extends AppCompatActivity {
    int sYear, sMonth, sDay,eYear,eMonth,eDay;
    private Button endDateChoose;
    TextView startDateDisplay,endDateDisplay,days,dayWord;
    private final static int DATE_DIALOG = 1;
    private ArrayList<Plan> list = new ArrayList<Plan>();
    WordDao dao=new WordDao(Left_plan.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.left_plan);

        days = (TextView) findViewById(R.id.days);
        dayWord = (TextView) findViewById(R.id.dayWord);

        startDateDisplay = (TextView) findViewById(R.id.startDateDisplay);

        endDateChoose = (Button) findViewById(R.id.endDateChoose);
        endDateDisplay = (TextView) findViewById(R.id.endDateDisplay);

        endDateChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showDialog(DATE_DIALOG);
            }
        });


        final Calendar sca = Calendar.getInstance();
        sYear = sca.get(GregorianCalendar.YEAR);
        sMonth = sca.get(GregorianCalendar.MONTH);
        sDay = sca.get(GregorianCalendar.DAY_OF_MONTH);

        dao.addPlan(0,sYear,sMonth,sDay);
        list=dao.getPlan();
        startDateDisplay.setText(new StringBuffer().append(list.get(0).getYear()).append("年").append(list.get(0).getMonth()+1).append("月").append(list.get(0).getDay()).append("日"));
        if (list.size()==1)
            endDateDisplay.setText("请选择结束日期");
        else
            endDateDisplay.setText(new StringBuffer().append(list.get(1).getYear()).append("年").append(list.get(1).getMonth()+1).append("月").append(list.get(1).getDay()).append("日"));


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sdate,edate;
            list=dao.getPlan();
            sdate=list.get(0).getYear()+"-"+(list.get(0).getMonth()+1)+"-"+list.get(0).getDay();
            if (list.size()==1) {
                days.setText("计划共有");
                dayWord.setText("每天需要背诵 个单词");
            } else {
                edate=list.get(1).getYear()+"-"+(list.get(1).getMonth()+1)+"-"+list.get(1).getDay();
                Date sd=sdf.parse(sdate);
                Date ed=sdf.parse(edate);
                int dayCount= (int) ((ed.getTime()-sd.getTime())/86400000);
                days.setText("计划共有"+dayCount+"天");
                if (dayCount>=321)
                    dayWord.setText("每天需要背诵1个单词");
                else
                    dayWord.setText("每天需要背诵"+321/dayCount+"个单词");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar eca = Calendar.getInstance();
        eYear = eca.get(Calendar.YEAR);
        eMonth = eca.get(Calendar.MONTH);
        eDay = eca.get(Calendar.DAY_OF_MONTH);

        }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, edateListener, eYear, eMonth, eDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */

    private DatePickerDialog.OnDateSetListener edateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            eYear = year;
            eMonth = monthOfYear;
            eDay = dayOfMonth;
            addPlan(eYear,eMonth,eDay);
            countOfDay(eYear,eMonth,eDay);
            endDateDisplay.setText(new StringBuffer().append(eYear).append("年").append(eMonth+1).append("月").append(eDay).append("日"));

        }
    };

    public void addPlan(int eYear,int eMonth,int eDay){
        if (list.size()==1){
            dao.addPlan(1,eYear,eMonth,eDay);
        }else {
            Plan plan = list.get(1);
            plan.setYear(eYear);
            plan.setMonth(eMonth);
            plan.setDay(eDay);
            dao.update(plan);
        }
    }

    public void countOfDay(int eYear,int eMonth,int eDay){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sdate,edate;
            list=dao.getPlan();
            sdate=list.get(0).getYear()+"-"+(list.get(0).getMonth()+1)+"-"+list.get(0).getDay();
            edate=eYear+"-"+(eMonth+1)+"-"+eDay;
            Date sd=sdf.parse(sdate);
            Date ed=sdf.parse(edate);
            int dayCount= (int) ((ed.getTime()-sd.getTime())/86400000);
            days.setText("计划共有"+dayCount+"天");
            if (dayCount>=321)
                dayWord.setText("每天需要背诵1个单词");
            else
                dayWord.setText("每天需要背诵"+321/dayCount+"个单词");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}