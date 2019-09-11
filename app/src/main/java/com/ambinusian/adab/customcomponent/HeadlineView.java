package com.ambinusian.adab.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ambinusian.adab.R;

public class HeadlineView extends RelativeLayout {

    TextView headlineTitle;

    public HeadlineView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.view_headline, this);
        int[] sets = {R.attr.headlineTitle};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence headline_title = typedArray.getText(typedArray.getIndex(0));
        typedArray.recycle();

        initComponents();

        setHeadlineTitle(headline_title);
    }

    private void initComponents() {
        headlineTitle = findViewById(R.id.headline_title);
    }

    private void setHeadlineTitle(CharSequence value){
        headlineTitle.setText(value);
    }
}
