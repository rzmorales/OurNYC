package com.example.ournyc.network;

import com.example.ournyc.model.ProgramModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ProgramService {
    String ENDPOINT = "resource/2j8u-wtju.json";

    @GET(ENDPOINT)
    Observable<List<ProgramModel>> getProgram();
}