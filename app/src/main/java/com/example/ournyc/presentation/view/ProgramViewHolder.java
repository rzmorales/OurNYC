package com.example.ournyc.presentation.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ournyc.R;
import com.example.ournyc.SendToFragment;
import com.example.ournyc.data.model.ProgramModel;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class ProgramViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "FINDME";
    SendToFragment sendToFragment;
//    TextView textView;


    public ProgramViewHolder(@NonNull View itemView, SendToFragment sendToFragment) {
        super(itemView);
        this.sendToFragment = sendToFragment;
//        textView = itemView.findViewById(R.id.text_sample);

    }

    public void onBind(final ProgramModel programModel) {
        Log.d(TAG, "onBind: " + programModel.getProgram_name());





//        textView.setText(programModel.getProgram_name());

        ((TextView) itemView.findViewById(R.id.text_sample)).setText(programModel.getProgram_name());

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


//        RxView.clicks(itemView).subscribe(v -> sendToFragment.sendToFragment(programModel.getProgram_name(), programDetailsList));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToFragment.sendToFragment(programModel.getProgram_name(), programDetailsList);
            }
        });

    }


}

