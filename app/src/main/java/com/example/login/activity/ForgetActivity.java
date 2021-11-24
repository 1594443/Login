package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.login.R;
import com.example.login.databinding.ActivityForgetBinding;
import com.example.login.util.ActivityCollector;
import com.example.login.util.CodeUtil;
import com.example.login.util.ToastUtil;

import java.util.regex.Pattern;

public class ForgetActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityForgetBinding activityForgetBinding;
    public static final int resultCode = 1002;
    String phone_number, password1, password2, verification_code, realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        activityForgetBinding = ActivityForgetBinding.inflate(getLayoutInflater());
        setContentView(activityForgetBinding.getRoot());
        //显示验证码
        activityForgetBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
        realCode = CodeUtil.getInstance().getCode().toLowerCase();
        activityForgetBinding.sure.setOnClickListener(this);
        activityForgetBinding.codeShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.code_show:
                activityForgetBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
                realCode = CodeUtil.getInstance().getCode().toLowerCase();
                break;
            case R.id.sure:
                phone_number = activityForgetBinding.phoneNumber.getText().toString().trim();
                password1 = activityForgetBinding.password1.getText().toString().trim();
                password2 = activityForgetBinding.password2.getText().toString().trim();
                verification_code = activityForgetBinding.verificationCode.getText().toString().trim();
                boolean matches = Pattern.matches("^1(3[0-9]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$", phone_number);
                if (!matches) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputCorrectPhoneNumber));
                } else if ("".equals(password1)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputPassword));
                } else if ("".equals(password2)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputPasswordAgain));
                } else if (!password1.equals(password2)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.TwoPasswordsAreNotTheSame));
                } else if ("".equals(verification_code)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputVerificationCode));
                } else if (!verification_code.equals(realCode)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputCorrectVerificationCode));
                }else {
                    intent = new Intent();
                    intent.putExtra("number", phone_number);
                    setResult(resultCode, intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}