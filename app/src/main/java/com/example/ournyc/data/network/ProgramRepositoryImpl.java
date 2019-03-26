package com.example.ournyc.data.network;

import com.example.ournyc.domain.ProgramRepository;
import com.example.ournyc.data.model.ProgramModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class ProgramRepositoryImpl implements ProgramRepository {
    private final Retrofit retrofit;

    public ProgramRepositoryImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    @Override
    public Observable<List<ProgramModel>> getPrograms() {
        return retrofit.create(ProgramService.class).getProgram();
    }
}
