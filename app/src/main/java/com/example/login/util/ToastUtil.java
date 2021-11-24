package com.example.login.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static Toast toast;

    @SuppressLint("ShowToast")
    public static void showMsg(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
