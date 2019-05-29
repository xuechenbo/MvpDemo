package com.kahuanbao.com.abother.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.jaeger.library.StatusBarUtil;
import com.kahuanbao.com.v.BaseActivity;
import com.kahuanbao.com.R;
import com.kahuanbao.com.v.activity.StartMainActivity;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 *
 * Created by Administrator on 2019/2/13.
 *
 *
 */

public class LuncherActivity extends BaseActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luncher);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iv = findViewById(R.id.iv);
        //Animation  an ne mai sheng
        //补间
        //initAnation();
        //属性
        //shuxin();//自定义插值器
        initZD();
    }
    /*
    * ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);
    //ObjectAnimator animator = ObjectAnimator.ofInt(Object object, String property, int ....values);
    //ObjectAnimator animator = ObjectAnimator.ofObject(Object object, String property, TypeEvaluator evaluator,Object....values);

    // 以ofFloat为例 参数说明：
    // Object object：需要操作的对象
    // String property：需要操作的对象的属性
    // float ....values：动画初始值 & 结束值（不固定长度）
    // 若是两个参数a,b，则动画效果则是从属性的a值到b值
    // 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
    // 以此类推
    // 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解

    //动画基本属性
    anim.setDuration(500);
    anim.setStartDelay(500);
    anim.setRepeatCount(0);
    anim.setRepeatMode(ValueAnimator.RESTART);

    animator.start();

    12   600      0000

    1    6000
    2    6000                        77              88                  110
    3    6000                    3 4 5 6 7 8   9 10 11 12 1 2    3  4 5  6 7 8
    4    7000
    5    7000
    6    7000
    7    7000       700
    8    7000       700
    9    7000       700
    10   8000       800
    11   8000       800
    12   8000       800
======================================================
    1    8000       800
    2    8000       800
    3    8000       800
    4    9000       900  +900

    92000-6000=86000
    */

    //92000-12000=80000-30000=50000


    private void initZD() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.85f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", 0.95f, 1.01f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 0.95f, 1.01f);
        //ObjectAnimator rotation = ObjectAnimator.ofFloat(iv, "rotation", 180f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alpha, scaleX, scaleY);
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {
                //PLayAndroid登录  可以跳到网页电影
                //getOperation().forward(LoginActivity.class);
                //getOperation().forward(StartMainActivity.class);
                finish();
               /* Intent mainIntent = new Intent(LuncherActivity.this, LoginActivity.class);
                startActivity(mainIntent);
                LuncherActivity.this.finish();*/
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void s1() {
        //ValueAnimator是属性动画最核心的一个类
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(300);
        //ValueAnimator.INFINITE  无线循环
        animator.setRepeatCount(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                Log.i("onAnimationUpdate", "currentValue：" + currentValue);
            }
        });
        animator.start();
        /*
        *   ValueAnimator帮我们计算了从0到1的过渡值，ofFloat方法是可以传入多个参数的，比如在1s内，从0过渡到6，再过渡到3，然后再过渡到10，就可以这样写：
            ValueAnimator animator = ValueAnimator.ofFloat(0f, 6f, 3f, 10f);
            animator.setDuration(1000);
            animator.start();
        * */
    }

    private void shuxin() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        // 设置插值器  自定义的
        animatorSet.setInterpolator(new JellyInterpolator());
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent mainIntent = new Intent(LuncherActivity.this, WebviewXFiveActivity.class);
                startActivity(mainIntent);
                LuncherActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    private void initAnation() {
        //组合动画
        AnimationSet setAnimation = new AnimationSet(true);

        Animation rotate = new RotateAnimation(0, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(1);

     /*   //平移动画
        Animation translate=new TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT,-0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT,0.5f,
                RELATIVE_TO_SELF,0
                , RELATIVE_TO_SELF,0);
        translate.setDuration(10000);
        translate.setRepeatCount(Animation.INFINITE);
        translate.setRepeatMode(Animation.REVERSE);*/

        //旋转
        Animation rotateAnimation2 = new RotateAnimation(0, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotateAnimation2.setDuration(3000);
        rotateAnimation2.setRepeatCount(1);


        //透明
        Animation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        alpha.setStartOffset(1000);
        alpha.setRepeatMode(Animation.REVERSE);

        //缩放
        Animation scale = new ScaleAnimation(1f, 0, 1f, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);
        scale.setRepeatCount(1);
        scale.setRepeatMode(Animation.REVERSE);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mainIntent = new Intent(LuncherActivity.this, WebviewXFiveActivity.class);
                startActivity(mainIntent);
                LuncherActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        setAnimation.addAnimation(alpha);
        setAnimation.addAnimation(rotate);
//        setAnimation.addAnimation(translate);
        setAnimation.addAnimation(scale);

        iv.startAnimation(setAnimation);
    }

    private void startMainActivity() {
        TimerTask delayTask = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(LuncherActivity.this, WebviewXFiveActivity.class);
                startActivity(mainIntent);
                LuncherActivity.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(delayTask, 2000);//延时两秒执行 run 里面的操作
    }

    @Override
    public void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setTranslucent(this);
    }
}
