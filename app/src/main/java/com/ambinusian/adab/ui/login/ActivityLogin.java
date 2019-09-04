package com.ambinusian.adab.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.FragmentChangeListener;

public class ActivityLogin extends AppCompatActivity implements FragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.login_layout,new FragmentWelcome()).commit();
    }


    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,
                R.anim.slide_out_right, R.anim.slide_in_right);
        fragmentTransaction.replace(R.id.login_layout,fragment,fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
