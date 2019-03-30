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
import com.jakewharton.rxbinding3.view.RxView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import java.util.ArrayList;
import java.util.List;


public class ContactInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "findme";

    private String mParam1;
    private List<String> programDetailList;


    public ContactInfoFragment() {}

    public static ContactInfoFragment newInstance(String programTitle, ArrayList<String> programDetailArrayList) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, programTitle);
        args.putStringArrayList(ARG_PARAM2, programDetailArrayList);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener((v, event) -> true);



        String description = Jsoup.parse(programDetailList.get(2)).text().toString();
        ((TextView) view.findViewById(R.id.Program_name)).setText(mParam1);


        ((TextView) view.findViewById(R.id.Program_date)).setText(description);
        ((TextView) view.findViewById(R.id.Program_category)).setText(Jsoup.parse(programDetailList.get(3)).text());
        Log.d("findme", "html: " + programDetailList.get(4));

        Document doc = Jsoup.parse(programDetailList.get(4));
        Element link = doc.select("a").first();

        String linkHref = link.attr("href");
        Log.d("findme", "href: " + linkHref);



        String how_to_apply_or_enroll_online = programDetailList.get(4);
        if (how_to_apply_or_enroll_online == null){
            how_to_apply_or_enroll_online= "THIS IS A TEST";
        }

        ((TextView) view.findViewById(R.id.Program_description)).setText(Jsoup.parse(how_to_apply_or_enroll_online).text());

        String how_to_getEmail = programDetailList.get(6);
        if (how_to_getEmail == null){
            how_to_getEmail= "THIS IS A TEST";
        }
        Log.d(TAG, "onViewCreated: " + Jsoup.parse(programDetailList.get(3)).attr("abs:href").toString());

        ((TextView) view.findViewById(R.id.Program_sample)).setText(Jsoup.parse(how_to_getEmail).text());


    }
}
