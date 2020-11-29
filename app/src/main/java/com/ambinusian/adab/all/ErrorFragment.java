package com.ambinusian.adab.all;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;

public class ErrorFragment extends Fragment {

    TextView errorTitle, errorSubtitle;
    UserPreferences userPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        errorTitle = view.findViewById(R.id.tv_error_title);
        errorSubtitle = view.findViewById(R.id.tv_error_subtitle);
        userPreferences = new UserPreferences(getContext());

        //set text size to all text view
        setTextSize();
    }

    private void setTextSize(){
        //multiple of text size
        float textSize = userPreferences.getTextSize();
        //set text size for each text view
        errorTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,errorTitle.getTextSize()*textSize);
        errorSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,errorSubtitle.getTextSize()*textSize);
    }
}
