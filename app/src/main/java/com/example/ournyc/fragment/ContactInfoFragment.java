package com.example.ournyc.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ournyc.R;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;


public class ContactInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "findme";

    private String mParam1;
    private List<String> programDetailList;


    public ContactInfoFragment() {
    }

    public static ContactInfoFragment newInstance(String programTitle, ArrayList<String> programDetailArrayList) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, programTitle);
        args.putStringArrayList(ARG_PARAM2, programDetailArrayList);
        fragment.setArguments(args);
        return fragment;
    }

    private static boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            programDetailList = getArguments().getStringArrayList(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(ContactInfoFragment::onTouch);

        //Set Program Name
        TextView program_name = view.findViewById(R.id.Program_name);
        program_name.setText(mParam1);


        //Set Program Date
        TextView program_date = view.findViewById(R.id.Program_date);
        String program_date_text = programDetailList.get(0);
        program_date.setText(program_date_text);


        //Set Program Description
        String program_description = Jsoup.parse(programDetailList.get(1)).text().toString();
        String parsed_program_description = assertProgramText(program_description);
        TextView program_description_view = view.findViewById(R.id.Program_description);
        program_description_view.setText(parsed_program_description);




        //Set How to apply
        TextView how_to_apply_view = view.findViewById(R.id.how_to_apply_text);
        String how_to_apply = programDetailList.get(2);
        String parsed_how_to_apply = getHrefTagValue(assertProgramText(how_to_apply));
        if (parsed_how_to_apply==null){
            how_to_apply_view.setVisibility(View.GONE);
            parsed_how_to_apply = "help?";
        }
        how_to_apply_view.setText(parsed_how_to_apply);

        if (parsed_how_to_apply==null){
            how_to_apply_view.setVisibility(View.GONE);
        }

        //Set Get Help
        TextView get_help_view = view.findViewById(R.id.Get_help_text);
        String get_help = programDetailList.get(3);
        String parsed_get_help = getHrefTagValue(assertProgramText(get_help));
        get_help_view.setText(parsed_get_help);


        Log.d("findme", "html: " + programDetailList.get(4));


//        Log.d("findme", "href: " + getHrefTagValue(assertProgramText(programDetailList.get(4))));


        assertProgramText(programDetailList.get(4));


//        ((TextView) view.findViewById(R.id.Program_description)).setText(Jsoup.parse(how_to_apply_or_enroll_online).text());
//
//        String how_to_getEmail = programDetailList.get(3);
//        if (how_to_getEmail == null) {
//            how_to_getEmail = "THIS IS A TEST";
//        }
////        Log.d(TAG, "onViewCreated: " + getHrefTagValue(assertProgramText(programDetailList.get(3))));
//
//
//        ((TextView) view.findViewById(R.id.Get_help_text)).setText(Jsoup.parse(how_to_getEmail).text());
    }


    public static String assertProgramText(String input) {
        if (input == null) {
            input = "This is a test";
        }
        return input;
    }


    public static String getHrefTagValue(String html) {
        if (html == null){
            return "apple";
        }
        return Jsoup.parse(html).select("a").first().attr("href");
    }

}
