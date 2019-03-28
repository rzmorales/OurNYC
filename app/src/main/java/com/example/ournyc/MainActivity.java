package com.example.ournyc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ournyc.data.model.ProgramModel;
import com.example.ournyc.data.network.ProgramRepositoryImpl;
import com.example.ournyc.data.network.RetrofitSingleton;
import com.example.ournyc.domain.ProgramRepository;
import com.example.ournyc.domain.ProgramUseCaseImpl;
import com.example.ournyc.fragment.ContactInfoFragment;
import com.example.ournyc.presentation.controller.ProgramAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SendToFragment {

    public static final String TAG = "FINDME";
    List<ProgramModel> services = new ArrayList<>();
    ProgramAdapter adapter;
    ProgramUsecase programUsecase;
    public static final String NAME = "name";
    public static final String DATE = "date";
    SendToFragment sendToFragment;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this.getApplicationContext()));


        sendToFragment = (SendToFragment) this;
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
                                    services.addAll(apiServiceList);

                                    Log.d(TAG, "find me" + apiServiceList.get(0).getProgram_name() + apiServiceList.get(0).getProgram_date());
                                    Log.d(TAG, "find me" + apiServiceList.get(10).getProgram_name() + apiServiceList.get(10).getProgram_date());


                                    adapter = new ProgramAdapter(apiServiceList, sendToFragment);
                                    recyclerView.setAdapter(adapter);

                                },

                                throwable -> Log.d(TAG, "" + throwable)

                        ));

        (findViewById(R.id.button)).setOnClickListener(v -> {
            services.remove(1);
            adapter.updateList(services);
        });


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
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

    @Override
    public void sendToFragment(String name, String help_inPerson) {

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, ContactInfoFragment.newInstance(name, help_inPerson)).addToBackStack(null).commit();
    }

}
