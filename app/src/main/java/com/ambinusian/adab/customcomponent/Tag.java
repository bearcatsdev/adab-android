package com.ambinusian.adab.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import com.ambinusian.adab.R;

public class Tag extends LinearLayout {

    TextView tagText;
    public Tag(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.layout_tag, this);
        int[] sets = {R.attr.tagText};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence tag_text = typedArray.getText(typedArray.getIndex(0));
        typedArray.recycle();

        initComponents();

        setTagText(tag_text);
    }

    private void initComponents() {
        tagText = (TextView) findViewById(R.id.tag_text);
    }

    private void setTagText(CharSequence value){
        tagText.setText(value);
    }
}
