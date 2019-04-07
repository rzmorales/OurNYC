package com.example.ournyc.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ournyc.ProgramUsecase;
import com.example.ournyc.R;
import com.example.ournyc.SendToFragment;
import com.example.ournyc.data.model.ProgramModel;
import com.example.ournyc.data.network.ProgramRepositoryImpl;
import com.example.ournyc.data.network.RetrofitSingleton;
import com.example.ournyc.domain.ProgramRepository;
import com.example.ournyc.domain.ProgramUseCaseImpl;
import com.example.ournyc.presentation.controller.ProgramAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class RecyclerViewFragment extends Fragment implements SearchView.OnQueryTextListener {

    public static final String TAG = "FINDME";
    List<ProgramModel> listOfProgramModels = new ArrayList<>();
    ProgramAdapter adapter;
    ProgramUsecase programUsecase;
    public static final String NAME = "name";
    public static final String DATE = "date";
    SendToFragment sendToFragment;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    View rootView;
    List<ProgramModel> programModelList;
    SearchView searchView;


    public RecyclerViewFragment() {
    }


    public static RecyclerViewFragment newInstance() {

        RecyclerViewFragment fragment = new RecyclerViewFragment();


        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof SendToFragment) {
            sendToFragment = (SendToFragment) context;
        } else {
            throw new RuntimeException();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        }

        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchview_state);

        final RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Retrofit retrofit = RetrofitSingleton.getInstance();
        final ProgramRepository programRepository = new ProgramRepositoryImpl(retrofit);

        programUsecase = new ProgramUseCaseImpl(programRepository);


        compositeDisposable.add(
                programUsecase
                        .getProgramList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                apiServiceList -> {
                                    Log.d(TAG, "find me" + apiServiceList);

                                    sortByDate(apiServiceList);

                                    Map<String, ProgramModel> serviceMap = new HashMap<>();
                                    for (ProgramModel programModel : apiServiceList) {
                                        if (!serviceMap.containsKey(programModel.getProgram_name())) {
                                            serviceMap.put(programModel.getProgram_name(), programModel);
                                        }
                                    }

                                    apiServiceList.clear();
                                    apiServiceList.addAll(serviceMap.values());
                                    listOfProgramModels.addAll(apiServiceList);

                                    Log.d(TAG, "find me" + apiServiceList.get(0).getProgram_name() + apiServiceList.get(0).getProgram_date());
                                    Log.d(TAG, "find me" + apiServiceList.get(10).getProgram_name() + apiServiceList.get(10).getProgram_date());

                                    programModelList = apiServiceList;
                                    searchView.setOnQueryTextListener(RecyclerViewFragment.this);

                                    adapter = new ProgramAdapter(apiServiceList, sendToFragment);
                                    recyclerView.setAdapter(adapter);

                                },

                                throwable -> Log.d(TAG, "" + throwable)

                        ));

//        (view.findViewById(R.id.button)).setOnClickListener(v -> {
//            listOfProgramModels.remove(1);
//            adapter.updateList(listOfProgramModels);
//        });


    }

    @Override
    public void onDetach() {
        sendToFragment = null;
        super.onDetach();
    }

    public void sortByDate(List<ProgramModel> apiServiceList) {
        apiServiceList.sort((o1, o2) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date firstDate = null;
            Date secondDate = null;
            try {
                firstDate = simpleDateFormat.parse(o1.getProgram_date());
                secondDate = simpleDateFormat.parse(o2.getProgram_date());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return secondDate != null ? secondDate.compareTo(firstDate) : 0;
        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {


        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        compositeDisposable.add(Observable.fromArray(programModelList)
                .flatMapIterable(programModelList -> programModelList)
                .filter(programModelList -> programModelList.getProgram_name().toLowerCase().startsWith(s.toLowerCase()))
                .toList()
                .subscribe(programModelList -> adapter.updateList(programModelList))
        );
        return false;
    }
}
