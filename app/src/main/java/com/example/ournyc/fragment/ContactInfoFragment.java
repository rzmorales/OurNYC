package com.example.ournyc.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
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
        Spanned spannedString = fromHtml(how_to_apply);
        Log.d("findme", "html: " + how_to_apply);
        how_to_apply_view.setText(spannedString);

        how_to_apply_view.setMovementMethod(LinkMovementMethod.getInstance());


//        String parsed_how_to_apply = assertProgramText(how_to_apply);
//        System.out.println(parseHTML( parsed_how_to_apply));
//        if (parsed_how_to_apply==null){
//            how_to_apply_view.setVisibility(View.GONE);
//            parsed_how_to_apply = "help?";
//            view.findViewById(R.id.How_to_apply_title).setVisibility(View.GONE);
//            view.findViewById(R.id.black_line2).setVisibility(View.GONE);
//            how_to_apply_view.setVisibility(View.GONE);
//        } else {}

        //Set Get Help
        TextView get_help_view = view.findViewById(R.id.Get_help_text);
        String get_help = programDetailList.get(3);

        get_help_view.setText(fromHtml(get_help));
        get_help_view.setMovementMethod(LinkMovementMethod.getInstance());







    }


    public static String assertProgramText(String input) {
        if (input == null) {
            input = "This is a test";
        }
        return input;
    }

    public static String parseHTML(String html){
        return Jsoup.parse(html).body().text();
    }


    public static String getHrefTagValue(String html) {
        if (html == null){
            return "apple";
        }

        for (int i = 0; i < html.length()-1; i++) {
            if (html.charAt(i) == '<' && html.charAt(i+1)=='a'){
                return Jsoup.parse(html).select("a").first().attr("href");

            }
        }

        return "";
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        if(html == null){
            return new SpannableString("");
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

}
