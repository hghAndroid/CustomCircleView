package com.itedu.mycustomview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.itedu.mycustomview.weight.CountDownView;
import com.itedu.mycustomview.weight.CountdownTimeView;
import com.itedu.mycustomview.weight.CustomCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyMainActivity extends AppCompatActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    boolean isPlay = false;
    private CountDownTimer countDownTimer;
    private CustomCircleView rect;
    private Button start;
    private LinearLayout layoutMain;
    private CustomCircleView circle;
    private CustomCircleView circle2;
    private CountDownView countDownView;
    private CountdownTimeView fs;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.e("非运算符~1", +~(1) + "");
        Log.e("非运算符~0", +~(0) + "");

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationView.cancelAnimation();
            }
        });

        rect = findViewById(R.id.circle_rect);
        start = findViewById(R.id.main_start);
        layoutMain = findViewById(R.id.main);
        start.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("MyMainActivity=", "onTouch ");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("MyMainActivity=", "onMove ");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("MyMainActivity=", "onUp");
                    break;
                default:
                    break;
            }
            return false;
        });
        fs = findViewById(R.id.fs);

        circle = findViewById(R.id.circle);

        circle2 = findViewById(R.id.circle2);
        countDownView = findViewById(R.id.countDownView);


        rect.setCustomCircleViewProgressBarText(new CustomCircleView.CustomCircleViewProgressBarText() {
            @Override
            public String generateText(CustomCircleView progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "/100";
            }
        });
        circle.setCustomCircleViewProgressBarText(new CustomCircleView.CustomCircleViewProgressBarText() {
            @Override
            public String generateText(CustomCircleView progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });
        circle2.setCustomCircleViewProgressBarText(new CustomCircleView.CustomCircleViewProgressBarText() {
            @Override
            public String generateText(CustomCircleView progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("millisUntilFinished==", millisUntilFinished / 1000 + "s");

                for (int i = 0; i <= 100; i++) {
                    rect.setProgressValue(i);
                    circle.setProgressValue(i);
                    circle2.setProgressValue(i);
                }
            }

            @Override
            public void onFinish() {
            }
        };


        countDownView.setOnFinishListener(new CountDownView.OnFinishListener() {
            @Override
            public void onFinish() {


                Toast.makeText(getApplicationContext(), "倒计时完毕！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    /**
     * 开始
     *
     * @param view
     */
    public void startPlay(View view) {


        Log.e("非运算符~1", ~1 + "");
        Log.e("非运算符~0", ~0 + "");
        Log.e("非运算符~2", ~2 + "");
        Log.e("非运算符~3", ~3 + "");
        Log.e("非运算符~4", ~4 + "");
        Log.e("非运算符~5", ~5 + "");
        Log.e("非运算符~6", ~6 + "");
        Log.e("非运算符~7", ~7 + "");
        Log.e("非运算符~8", ~8 + "");
        Log.e("非运算符~9", ~9 + "");
        Log.e("非运算符~10", ~10 + "");
        Log.e("非运算符~11", ~11 + "");
        Log.e("非运算符~111", ~111 + "");

        Log.e("MyMainActivity=", "点击了按钮");

        if (!isPlay) {
            countDownTimer.start();
            isPlay = true;
        }
        if (!countDownView.isStarted()) {
            countDownView.start();
        }


//        Navigation.findNavController(view).navigateUp();
    }

    /**
     * 重置
     *
     * @param view
     */
    public void reset(View view) {
        if (isPlay) {
            rect.setProgressValue(0);
            circle.setProgressValue(0);
            circle2.setProgressValue(0);
            countDownTimer.cancel();
            isPlay = false;

            fs.resetState();

        }

    }

    public void countdowntime(View view) {

        Toast.makeText(this, "验证码已发送", Toast.LENGTH_LONG).show();
    }

    /**
     * 跳转
     *
     * @param view
     */
    public void onClickJump(View view) {


        startActivity(new Intent(MyMainActivity.this, SecondActivity.class));

    }
}
