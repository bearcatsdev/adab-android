package com.ambinusian.adab.customcomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ambinusian.adab.R;

public class SectionView extends RelativeLayout {

    TextView sectionTitle;
    TextView sectionSubtitle;

    public SectionView(Context context) {
        super(context);
        initView();

    }

    public SectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);

    }

    public SectionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        initView();
        getAttrs(attrs, defStyle);

    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(infService);
        View v = li.inflate(R.layout.view_section, this, false);
        addView(v);

        sectionTitle = findViewById(R.id.section_title);
        sectionSubtitle = findViewById(R.id.section_subtitle);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SectionView);

        setTypeArray(typedArray);
    }


    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SectionView, defStyle, 0);
        setTypeArray(typedArray);
    }


    private void setTypeArray(TypedArray typedArray) {
        String sectionTitle = typedArray.getString(R.styleable.SectionView_sectionTitle);
        String sectionSubtitle = typedArray.getString(R.styleable.SectionView_sectionSubtitle);
        this.sectionTitle.setText(sectionTitle);

        if(sectionSubtitle!=null) {
            this.sectionSubtitle.setText(sectionSubtitle);
            this.sectionSubtitle.setVisibility(VISIBLE);
        }
        typedArray.recycle();
    }

}
