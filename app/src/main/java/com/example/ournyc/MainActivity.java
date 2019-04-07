package com.example.ournyc;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.ournyc.fragment.ContactInfoFragment;
import com.example.ournyc.fragment.RecyclerViewFragment;
import com.example.ournyc.fragment.SplashScreenFragment;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements SendToFragment {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, SplashScreenFragment.newInstance())
                .addToBackStack(null)
                .commit();




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

                WebView webView;

                return true;

            case R.id.Robert_LinkedIn:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;


    }
}
