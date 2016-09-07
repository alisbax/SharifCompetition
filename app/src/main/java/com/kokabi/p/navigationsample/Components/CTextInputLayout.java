package com.kokabi.p.navigationsample.Components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.AttributeSet;

import com.kokabi.p.navigationsample.Help.FontChange;
import com.kokabi.p.navigationsample.Help.Constants;

public class CTextInputLayout extends TextInputLayout {
    Context context;

    public CTextInputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public CTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CTextInputLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init() {
        if (isInEditMode())
            return;
        setOnTouchListener(null);
        FontChange.setFontTextInputLayout(this, Constants.font.SANS_MEDIUM, context);
    }

    public void setErrorCustom(String str) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new FontSpan(FontChange.getTypeface(Constants.font.SANS, context)), 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setError(ss);
    }

    private static final class FontSpan extends MetricAffectingSpan {

        private final Typeface mNewFont;

        private FontSpan(Typeface newFont) {
            mNewFont = newFont;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setTypeface(mNewFont);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            paint.setTypeface(mNewFont);
        }

    }
}