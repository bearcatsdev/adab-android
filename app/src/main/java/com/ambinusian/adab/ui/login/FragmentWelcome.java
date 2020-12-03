package com.ambinusian.adab.ui.login;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.FragmentChangeListener;
import com.ambinusian.adab.preferences.UserPreferences;
import com.google.android.material.button.MaterialButton;

public class FragmentWelcome extends Fragment {

    MaterialButton letsgo;
    UserPreferences userPreferences;
    TextView tvHiThere;

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
        tvHiThere = view.findViewById(R.id.tv_hi_there);
        userPreferences = new UserPreferences(getContext());

        //set text attributes
        setTextSize();
        setTextTypeface();

        letsgo.setOnClickListener(view1 -> {
            FragmentChangeListener fc = (FragmentChangeListener) getActivity();
            assert fc != null;
            fc.replaceFragment(new FragmentLogin());
        });
    }

    private void setTextSize(){
        //multiple of text size
        float textSize = userPreferences.getTextSize();
        //set text size for each text view
        tvHiThere.setTextSize(TypedValue.COMPLEX_UNIT_PX,tvHiThere.getTextSize()*textSize);
        letsgo.setTextSize(TypedValue.COMPLEX_UNIT_PX,letsgo.getTextSize()*textSize);
    }

    private void setTextTypeface(){
        //get font type
        Typeface textTypeface = userPreferences.getTextTypeface();
        //set font type for each text view
        tvHiThere.setTypeface(textTypeface, tvHiThere.getTypeface().getStyle());
        letsgo.setTypeface(textTypeface, letsgo.getTypeface().getStyle());
    }
}
