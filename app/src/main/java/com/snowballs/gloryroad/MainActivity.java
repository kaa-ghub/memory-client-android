package com.snowballs.gloryroad;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.snowballs.gloryroad.fragment.SplashFragmentDirections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fromSplash();
            }
        }, 1000);
    }

    private void fromSplash() {
        NavDirections action =
                SplashFragmentDirections.actionSplashFragmentToSearchFragment();
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(action);
    }
}
