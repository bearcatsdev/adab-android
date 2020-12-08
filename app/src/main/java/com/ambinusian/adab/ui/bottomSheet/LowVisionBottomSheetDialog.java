package com.ambinusian.adab.ui.bottomSheet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.ambinusian.adab.R;
import com.ambinusian.adab.preferences.UserPreferences;
import com.ambinusian.adab.ui.student.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class LowVisionBottomSheetDialog extends BottomSheetDialogFragment {

    UserPreferences userPreferences;
    SaveButtonListener saveButtonListener;
    Slider textSizeSlider;
    Spinner textTypefaceSpinner;
    MaterialButton btnSave;
    TextView tvTitleExample, tvSubtitleExample, tvFontSize, tvFontStyle, tvHighContrast;
    SwitchMaterial swHighContrast;
    float defaultTitleSize, defaultSubtitleSize;

    int selectedTypefaceID;
    boolean viewJustLoaded = true;

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
        tvFontSize = v.findViewById(R.id.tv_font_size);
        tvFontStyle = v.findViewById(R.id.tv_font_style);
        tvHighContrast = v.findViewById(R.id.tv_high_contrast);
        swHighContrast = v.findViewById(R.id.sw_high_contrast);

        defaultTitleSize = tvTitleExample.getTextSize();
        defaultSubtitleSize = tvSubtitleExample.getTextSize();

        userPreferences = new UserPreferences(v.getContext());
        textSizeSlider.setValue(userPreferences.getTextSize());
        swHighContrast.setChecked(userPreferences.getHighContrast());

        // edit font family using this spinner
        Spinner spinner = (Spinner) v.findViewById(R.id.text_typeface_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.typeface_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //set high contrast if enabled
        if(userPreferences.getHighContrast()){
            setHighConstrastTheme();
        }

        //set text attributes
        setTextSize(userPreferences.getTextSize());
        setTextTypeface(userPreferences.getTextTypeface());

        textSizeSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                changeTextSize(value);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.setTextSize(textSizeSlider.getValue());
                userPreferences.setTextTypeface(selectedTypefaceID);
                userPreferences.setHighContrast(swHighContrast.isChecked());
                saveButtonListener.onClick();
            }
        });

        //spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //set
                if (viewJustLoaded){
                    viewJustLoaded = false;
                    spinner.setSelection(userPreferences.getTextTypefaceFamilyID());
                } else {
                    selectedTypefaceID = position;
                    setTextTypeface(userPreferences.getTextTypeface(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //do nothing
            }

        });

        return v;
    }

    private void setHighConstrastTheme() {
        tvHighContrast.setTextColor(getResources().getColor(android.R.color.white));
        tvFontStyle.setTextColor(getResources().getColor(android.R.color.white));
        tvFontSize.setTextColor(getResources().getColor(android.R.color.white));
        tvTitleExample.setTextColor(getResources().getColor(R.color.buttonColor));
        tvSubtitleExample.setTextColor(getResources().getColor(android.R.color.white));
        btnSave.setBackgroundColor(getResources().getColor(R.color.buttonColor));
    }

    private void setTextSize(float textSize){
        tvTitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultTitleSize*textSize);
        tvSubtitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultSubtitleSize*textSize);
        tvFontSize.setTextSize(TypedValue.COMPLEX_UNIT_PX,tvFontSize.getTextSize()*textSize);
        tvFontStyle.setTextSize(TypedValue.COMPLEX_UNIT_PX,tvFontStyle.getTextSize()*textSize);
        tvHighContrast.setTextSize(TypedValue.COMPLEX_UNIT_PX,tvHighContrast.getTextSize()*textSize);
    }

    private void changeTextSize(float textSize){
        tvTitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultTitleSize*textSize);
        tvSubtitleExample.setTextSize(TypedValue.COMPLEX_UNIT_PX,defaultSubtitleSize*textSize);
    }

    private void setTextTypeface(Typeface textTypeface){
        tvTitleExample.setTypeface(textTypeface, tvTitleExample.getTypeface().getStyle());
        tvSubtitleExample.setTypeface(textTypeface, tvSubtitleExample.getTypeface().getStyle());
        tvFontSize.setTypeface(textTypeface, tvFontSize.getTypeface().getStyle());
        tvFontStyle.setTypeface(textTypeface, tvFontStyle.getTypeface().getStyle());
    }
}
