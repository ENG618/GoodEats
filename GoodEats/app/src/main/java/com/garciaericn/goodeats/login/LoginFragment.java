package com.garciaericn.goodeats.login;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garciaericn.goodeats.R;
import com.google.android.gms.common.SignInButton;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 11/10/14.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    public static final String TAG = "com.garciaericn.goodeats.login.LoginFragment.TAG";

    public LoginFragment() {

    }

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        SignInButton signInButton = (SignInButton) view.findViewById(R.id.g_plus_login);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.g_plus_login:
                Toast.makeText(getActivity(), "Sign in now", Toast.LENGTH_SHORT).show();
                // TODO: Start login process here
                break;
            default:
                // If default action is needed.
                break;
        }
    }
}
