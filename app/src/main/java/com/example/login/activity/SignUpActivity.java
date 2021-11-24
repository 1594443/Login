package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.login.R;
import com.example.login.databinding.ActivitySignUpBinding;
import com.example.login.util.CodeUtil;
import com.example.login.util.ToastUtil;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySignUpBinding activitySignUpBinding;
    public static final int resultCode = 1001;
    String phone_number, password1, password2, verification_code, realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());
        //显示验证码
        activitySignUpBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
        activitySignUpBinding.register.setOnClickListener(this);
        activitySignUpBinding.codeShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.code_show:
                activitySignUpBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
                realCode = CodeUtil.getInstance().getCode().toLowerCase();
                break;
            case R.id.register:
                phone_number = activitySignUpBinding.phoneNumber.getText().toString().trim();
                password1 = activitySignUpBinding.password1.getText().toString().trim();
                password2 = activitySignUpBinding.password2.getText().toString().trim();
                verification_code = activitySignUpBinding.verificationCode.getText().toString().trim();
                boolean matches = Pattern.matches("^1(3[0-9]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$", phone_number);
                Log.e("TAG", "onClick: " + matches);
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
                } else {
                    intent = new Intent();
                    intent.putExtra("number", phone_number);
                    setResult(resultCode, intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}