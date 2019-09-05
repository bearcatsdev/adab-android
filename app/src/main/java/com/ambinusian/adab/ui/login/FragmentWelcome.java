package com.ambinusian.adab.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.FragmentChangeListener;
import com.google.android.material.button.MaterialButton;

public class FragmentWelcome extends Fragment {

    MaterialButton letsgo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        letsgo = view.findViewById(R.id.btn_letsgo);

        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(new FragmentLogin());
            }
        });

    }
}
