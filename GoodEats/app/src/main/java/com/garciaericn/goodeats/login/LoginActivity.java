package com.garciaericn.goodeats.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.garciaericn.goodeats.R;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/10/14.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.login_container, SplashScreenFragment.getInstance(), SplashScreenFragment.TAG)
//                .commit();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.login_container, LoginFragment.getInstance(), LoginFragment.TAG)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
