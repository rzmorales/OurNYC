package com.example.ournyc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ournyc.controller.ProgramAdapter;
import com.example.ournyc.model.ProgramModel;
import com.example.ournyc.network.ProgramService;
import com.example.ournyc.network.RetrofitSingleton;

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

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FINDME";
    List<ProgramModel> services = new ArrayList<>();
    ProgramAdapter adapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.RecyclerViewID);

        compositeDisposable.add(
                RetrofitSingleton.getInstance().create(ProgramService.class)
                        .getProgram()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                apiServiceList -> {
                                    Log.d("find me", "find me" + apiServiceList);

                                    sortByDate(apiServiceList);

                                    Map<String, ProgramModel> serviceMap = new HashMap<>();
                                    for (ProgramModel a : apiServiceList) {
                                        if (!serviceMap.containsKey(a.getProgram_name())) {
                                            serviceMap.put(a.getProgram_name(), a);
                                        }
                                    }

                                    apiServiceList.clear();
                                    apiServiceList.addAll(serviceMap.values());
                                    services.addAll(apiServiceList);

                                    Log.d("find me", "find me" + apiServiceList.get(0).getProgram_name() + apiServiceList.get(0).getDate());
                                    Log.d("find me", "find me" + apiServiceList.get(10).getProgram_name() + apiServiceList.get(10).getDate());


                                    adapter = new ProgramAdapter(apiServiceList);
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this.getApplicationContext()));

                                },

                                throwable -> Log.d("find me", "" + throwable)

                        ));

    }

    public void sortByDate(List<ProgramModel> apiServiceList) {
        apiServiceList.sort((o1, o2) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date firstDate = null;
            Date secondDate = null;
            try {
                firstDate = simpleDateFormat.parse(o1.getDate());
                secondDate = simpleDateFormat.parse(o2.getDate());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return secondDate.compareTo(firstDate);
        });
    }


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }
}
