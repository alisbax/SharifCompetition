package com.kokabi.p.navigationsample.Components;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.R;


/**
 * Created by P.kokabi on 8/6/2016.
 */
public abstract class DialogGeneralThreeButton extends Dialog implements View.OnClickListener {

    private CButton first_btn, second_btn, third_btn;
    TextView title_tv;
    String titleText, firstButtonTitle, secondButtonTitle, thirdButtonTitle;

    public DialogGeneralThreeButton(@Nullable String titleText, @Nullable String firstButtonTitle
            , String secondButtonTitle, @Nullable String thirdButtonTitle) {
        super(AppController.getCurrentContext());
        this.titleText = titleText;
        this.firstButtonTitle = firstButtonTitle;
        this.secondButtonTitle = secondButtonTitle;
        this.thirdButtonTitle = thirdButtonTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_general_three_button);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        findViews();

        title_tv.setText(titleText);
        first_btn.setText(firstButtonTitle);
        second_btn.setText(secondButtonTitle);
        third_btn.setText(thirdButtonTitle);

        setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first_btn:
                firstAction();
                dismiss();
                break;
            case R.id.second_btn:
                secondAction();
                dismiss();
                break;
            case R.id.third_btn:
                thirdAction();
                dismiss();
                break;
        }
    }

    private void findViews() {
        first_btn = (CButton) findViewById(R.id.first_btn);
        second_btn = (CButton) findViewById(R.id.second_btn);
        third_btn = (CButton) findViewById(R.id.third_btn);

        title_tv = (TextView) findViewById(R.id.title_tv);

        onClickListeners();
    }

    private void onClickListeners() {
        first_btn.setOnClickListener(this);
        second_btn.setOnClickListener(this);
        third_btn.setOnClickListener(this);
    }

    public abstract void firstAction();

    public abstract void secondAction();

    public abstract void thirdAction();

}
