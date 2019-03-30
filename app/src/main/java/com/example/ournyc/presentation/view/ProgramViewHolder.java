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

public class ProgramViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "FINDME";
    SendToFragment sendToFragment;


    public ProgramViewHolder(@NonNull View itemView, SendToFragment sendToFragment) {
        super(itemView);
        this.sendToFragment = sendToFragment;

    }

    public void onBind(final ProgramModel programModel) {
        Log.d(TAG, "onBind: " + programModel.getProgram_name());
        ((TextView) itemView.findViewById(R.id.text_sample)).setText(programModel.getProgram_name());

        ArrayList<String> programDetailsList = new ArrayList<>();
        programDetailsList.add(programModel.getProgram_date());
        programDetailsList.add(programModel.getAge_group());
        programDetailsList.add(programModel.getProgram_description());
        programDetailsList.add(programModel.getGet_help_summary());
        programDetailsList.add(programModel.getHow_to_apply_or_enroll_online());
        programDetailsList.add(programModel.getGovernment_agency());
        programDetailsList.add(programModel.getGet_help_by_email());



        itemView.setOnClickListener(v -> sendToFragment.sendToFragment(programModel.getProgram_name(), programDetailsList));

    }


}

