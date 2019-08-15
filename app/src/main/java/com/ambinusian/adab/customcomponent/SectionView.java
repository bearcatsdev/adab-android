package com.ambinusian.adab.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ambinusian.adab.R;

public class SectionView extends RelativeLayout {

    TextView sectionTitle;
    TextView sectionSubtitle;

    public SectionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){
        inflate(context, R.layout.section_view, this);
        int[] sets = {R.attr.sectionTitle, R.attr.sectionTitle};
        TypedArray typedArray = context.obtainStyledAttributes(attrs, sets);
        CharSequence title = typedArray.getText(typedArray.getIndex(0));
        CharSequence subtitle = typedArray.getText(typedArray.getIndex(1));

        typedArray.recycle();

        initComponents();

        setSectionTitle(title);
        setSectionSubtitle(subtitle);
    }

    private void initComponents() {
        sectionTitle = findViewById(R.id.section_title);
        sectionSubtitle = findViewById(R.id.section_subtitle);
    }

    private void setSectionTitle(CharSequence value){
        sectionTitle.setText(value);
    }

    public void setSectionSubtitle(CharSequence value) {
        sectionSubtitle.setText(value);
    }
}
