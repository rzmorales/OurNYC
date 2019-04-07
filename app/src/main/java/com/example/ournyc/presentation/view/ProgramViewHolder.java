package com.example.ournyc.presentation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ournyc.R;
import com.example.ournyc.SendToFragment;
import com.example.ournyc.data.model.ProgramModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "FINDME";
    SendToFragment sendToFragment;
    TextView calendarName;
    TextView calendarDate;
    LinearLayout linearLayout;
    CardView cardView;


    public ProgramViewHolder(@NonNull View itemView, SendToFragment sendToFragment) {
        super(itemView);
        this.sendToFragment = sendToFragment;
        calendarName = itemView.findViewById(R.id.calendar_name);
        calendarDate = itemView.findViewById(R.id.calendar_number_date);
        linearLayout = itemView.findViewById(R.id.calendar);
        cardView = itemView.findViewById(R.id.cardview_Layout);

    }

    public void onBind(final ProgramModel programModel) {


        Map<String, String> intToStringMonths = new HashMap<>();
        intToStringMonths.put("01","JAN");
        intToStringMonths.put("02","FEB");
        intToStringMonths.put("03","MAR");
        intToStringMonths.put("04","APR");
        intToStringMonths.put("05","MAY");
        intToStringMonths.put("06","JUNE");
        intToStringMonths.put("07","JULY");
        intToStringMonths.put("08","AUG");
        intToStringMonths.put("09","SEPT");
        intToStringMonths.put("10","OCT");
        intToStringMonths.put("11","NOV");
        intToStringMonths.put("12","DEC");

        String monthNum = programModel.getProgram_date().substring(5,7);
        String monthDayNum = programModel.getProgram_date().substring(8,10);

        int MonthString = Integer.parseInt(monthNum);

        String getMonthName = intToStringMonths.get(monthNum);

        Log.d(TAG, "onBind: " + programModel.getProgram_name());
        Log.d(TAG, "onBind: " + getMonthName);
        Log.d(TAG, "onBind: " + monthDayNum);




//        calendarName.setText(programModel.getProgram_name());

        TextView textView_Program_Name = itemView.findViewById(R.id.text_sample);
        textView_Program_Name.setText(programModel.getProgram_name());

        calendarName.setText(getMonthName);
        calendarDate.setText(monthDayNum);

        calendarName.requestLayout();
        calendarDate.requestLayout();
        linearLayout.requestLayout();
        cardView.requestLayout();

//        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) textView_Program_Name.getLayoutParams();
//        params.width = cardView.getWidth() - calendarName.getWidth();
//        textView_Program_Name.setLayoutParams(params);
//
////
//
//        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) calendarName.getLayoutParams();
//        params.height = 30;
//        calendarName.setLayoutParams(params);

//        calendarName.setText("APR");

//        ViewGroup.LayoutParams params2 = calendarDate.getLayoutParams();
//        params2.height = 15;
//        calendarDate.setLayoutParams(params2);

//        calendarDate.setText("6");

//        calendarName.getLayoutParams().height = linearLayout.getHeight() / 4;
//        calendarDate.getLayoutParams().height = linearLayout.getHeight() * 3 / 4;
//
//
//        calendarName.setHeight(linearLayout.getHeight() / 4);
//        calendarDate.setHeight(linearLayout.getHeight() * 3 / 4);



        ArrayList<String> programDetailsList = new ArrayList<>();
        programDetailsList.add(programModel.getProgram_date());
        programDetailsList.add(programModel.getProgram_description());

        programDetailsList.add(programModel.getHow_to_apply_summary());

        programDetailsList.add(programModel.getGet_help_summary());

        programDetailsList.add(programModel.getGovernment_agency());
        programDetailsList.add(programModel.getHow_to_apply_or_enroll_online());
        programDetailsList.add(programModel.getAge_group());
        programDetailsList.add(programModel.getGovernment_agency());
        programDetailsList.add(programModel.getPlain_language_eligibility());


//        RxView.clicks(itemView).subscribe(v -> sendToContactInfoFragment.sendToContactInfoFragment(programModel.getProgram_name(), programDetailsList));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToFragment.sendToContactInfoFragment(programModel.getProgram_name(), programDetailsList);
            }
        });

    }


}

