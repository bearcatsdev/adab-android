package com.ambinusian.adab.customcomponent;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.ambinusian.adab.R;
import com.google.android.material.card.MaterialCardView;

public class Tag extends LinearLayout {

    TextView tagText;
    MaterialCardView materialCardView;


    public Tag(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.layout_tag, this);
        initComponents();
        int[] sets = {R.attr.tagText};

        // Tag Color
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence tag_text = typedArray.getText(typedArray.getIndex(0));
        typedArray.recycle();

        setTagText(tag_text);
    }

    private void initComponents() {
        tagText = findViewById(R.id.tag_text);
        materialCardView = findViewById(R.id.materialCardView);
    }

    private void setTagText(CharSequence value){
        tagText.setText(value);
    }
}
