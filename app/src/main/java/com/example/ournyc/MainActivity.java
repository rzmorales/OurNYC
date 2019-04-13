package com.example.ournyc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ournyc.data.model.ProgramModel;
import com.example.ournyc.fragment.ContactInfoFragment;
import com.example.ournyc.fragment.RecyclerViewFragment;
import com.example.ournyc.fragment.SplashScreenFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements SendToFragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String githubUrl = "https://github.com/rzmorales";
    private  String linkedInUrl = "https://www.linkedin.com/in/robert-zarate-morales/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, SplashScreenFragment.newInstance())
                .addToBackStack(null)
                .commit();


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

    @Override
    public void sendToContactInfoFragment(String name, ArrayList<String> programDetailsList) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, ContactInfoFragment.newInstance(name, programDetailsList))
                .addToBackStack(null).commit();
    }

    @Override
    public void sendToRecyclerViewFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, RecyclerViewFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Robert_Github:

                Uri parsedGitHubPage = Uri.parse(githubUrl);
                Intent toGitHubPage = new Intent(Intent.ACTION_VIEW);
                toGitHubPage.setData(parsedGitHubPage);
                startActivity(toGitHubPage);


                return true;

            case R.id.Robert_LinkedIn:
                Uri parsedLinkedInUrl = Uri.parse(linkedInUrl);
                Intent toLinkedInPage = new Intent(Intent.ACTION_VIEW);
                toLinkedInPage.setData(parsedLinkedInUrl);
                startActivity(toLinkedInPage);



                return true;

            default:
                Toast.makeText(getApplicationContext(),"No option selected",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;


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

}
