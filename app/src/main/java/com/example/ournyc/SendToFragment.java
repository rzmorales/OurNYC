package com.example.ournyc;

import android.content.Context;
import android.content.Intent;

import com.example.ournyc.data.model.ProgramModel;

import java.util.ArrayList;
import java.util.List;

public interface SendToFragment {

    void sendToContactInfoFragment(String name, ArrayList<String> programDetailsList);
    void sendToRecyclerViewFragment();

}
