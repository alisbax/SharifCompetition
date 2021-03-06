package com.kokabi.p.navigationsample.Components;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.kokabi.p.navigationsample.Help.FontChange;
import com.kokabi.p.navigationsample.Help.Constants;

public class CTextViewSans extends AppCompatTextView {
    Context ctx;

    public CTextViewSans(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
        init();
    }

    public CTextViewSans(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        init();
    }

    public CTextViewSans(Context context) {
        super(context);
        ctx = context;
        init();
    }

    public void init() {
        if (isInEditMode())
            return;
        setOnTouchListener(null);
        FontChange.setFontTextView(this, Constants.font.SANS, ctx);
    }
}