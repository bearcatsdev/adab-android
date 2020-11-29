package com.ambinusian.adab.ui.bottomSheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

public class LowVisionBottomSheetDialog extends BottomSheetDialogFragment {

    UserPreferences userPreferences;
    SaveButtonListener saveButtonListener;
    Slider textSizeSlider;
    MaterialButton btnSave;
    TextView tvTitleExample;
    TextView tvSubtitleExample;
    float defaultTitleSize;
    float defaultSubtitleSize;

    public LowVisionBottomSheetDialog(SaveButtonListener saveButtonListener){
        this.saveButtonListener = saveButtonListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.low_vision_bottom_sheet,
                container, false);

        textSizeSlider = v.findViewById(R.id.text_size_slider);
        btnSave = v.findViewById(R.id.btn_save);
        tvTitleExample = v.findViewById(R.id.tv_title_example);
        tvSubtitleExample = v.findViewById(R.id.tv_subtitle_example);
        defaultTitleSize = tvTitleExample.getTextSize();
        defaultSubtitleSize = tvSubtitleExample.getTextSize();

        userPreferences = new UserPreferences(v.getContext());
        textSizeSlider.setValue(userPreferences.getTextSize());

        setTextSize(userPreferences.getTextSize());

        textSizeSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                setTextSize(value);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.setTextSize(textSizeSlider.getValue());
                saveButtonListener.onClick();
            }
        });

        return v;
    }

    private void setTextSize(float textSize){
        tvTitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultTitleSize*textSize);
        tvSubtitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultSubtitleSize*textSize);
    }
}
