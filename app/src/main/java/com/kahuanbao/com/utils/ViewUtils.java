package com.kahuanbao.com.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kahuanbao.com.R;


public class ViewUtils {
    /**
     * 自定义吐司
     *
     * @param context  上下文
     * @param text     吐司内容
     * @param duration 显示时长
     */
    public static void makeToast(Context context, String text, int duration) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_toast_layout, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }
}
