package com.example.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.databinding.ActivivtyLoginBinding;
import com.example.login.util.ActivityCollector;
import com.example.login.util.CodeUtil;
import com.example.login.util.ToastUtil;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivivtyLoginBinding activivtyLoginBinding;
    public static final int RequestCode = 1000;
    String phone_number, user_password, verification_code, realCode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        activivtyLoginBinding = ActivivtyLoginBinding.inflate(getLayoutInflater());
        setContentView(activivtyLoginBinding.getRoot());

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        //显示验证码
        activivtyLoginBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
        realCode = CodeUtil.getInstance().getCode().toLowerCase();
        activivtyLoginBinding.signUp.setOnClickListener(this);
        activivtyLoginBinding.forgetPassword.setOnClickListener(this);
        activivtyLoginBinding.login.setOnClickListener(this);
        activivtyLoginBinding.codeShow.setOnClickListener(this);
        activivtyLoginBinding.rememberMe.setOnClickListener(this);

        boolean isChcked = sharedPreferences.getBoolean("ischeck", false);
        if (isChcked) {
            activivtyLoginBinding.phoneNumber.setText(sharedPreferences.getString("username", ""));
            activivtyLoginBinding.rememberMe.setChecked(true);
        }
    }

    @Override
    @SuppressLint("CommitPrefEdits")
    public void onClick(View v) {
        Intent intent;
        phone_number = activivtyLoginBinding.phoneNumber.getText().toString().trim();
        user_password = activivtyLoginBinding.userPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.code_show:
                activivtyLoginBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
                realCode = CodeUtil.getInstance().getCode().toLowerCase();
                break;
            case R.id.sign_up:
                intent = new Intent(this, SignUpActivity.class);
                startActivityForResult(intent, RequestCode);
                break;
            case R.id.forget_password:
                intent = new Intent(this, ForgetActivity.class);
                startActivityForResult(intent, RequestCode);
                break;
            case R.id.login:
                verification_code = activivtyLoginBinding.verificationCode.getText().toString().trim();
                boolean matches = Pattern.matches("^1(3[0-9]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$", phone_number);
                if (!matches) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputCorrectPhoneNumber));
                } else if ("".equals(verification_code)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputVerificationCode));
                } else if (!verification_code.equals(realCode)) {
                    ToastUtil.showMsg(getApplicationContext(), getString(R.string.InputCorrectVerificationCode));
                    activivtyLoginBinding.codeShow.setImageBitmap(CodeUtil.getInstance().createBitmap());
                    realCode = CodeUtil.getInstance().getCode().toLowerCase();
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.remember_me:
                editor = sharedPreferences.edit();
                editor.putString("username", phone_number);
                boolean isChceck = activivtyLoginBinding.rememberMe.isChecked();
                editor.putBoolean("ischeck",isChceck);
                if (isChceck) {
                    editor.putString("username", phone_number);
                } else {
                    editor.remove("username");
                }
                editor.apply();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode && resultCode == SignUpActivity.resultCode) {
            if (data != null) {
                phone_number = data.getStringExtra("number");
                activivtyLoginBinding.phoneNumber.setText(phone_number);
            }
        } else if (requestCode == RequestCode && resultCode == ForgetActivity.resultCode) {
            if (data != null) {
                phone_number = data.getStringExtra("number");
                activivtyLoginBinding.phoneNumber.setText(phone_number);
            }
        }
    }

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            ActivityCollector.finishAll();
        } else {
            ToastUtil.showMsg(getApplicationContext(), "再次点击退出！");
            mBackPressed = System.currentTimeMillis();
        }
    }
}
