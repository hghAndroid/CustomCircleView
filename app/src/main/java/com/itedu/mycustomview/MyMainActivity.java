package com.itedu.mycustomview;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.itedu.mycustomview.weight.CountDownView;
import com.itedu.mycustomview.weight.CountdownTimeView;
import com.itedu.mycustomview.weight.CustomCircleView;

import androidx.navigation.fragment.NavHostFragment;


public class MyMainActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    boolean isPlay = false;
    private CustomCircleView rect;
    private CustomCircleView circle;
    private CustomCircleView circle2;
    private CountDownView countDownView;
    private CountdownTimeView fs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rect = findViewById(R.id.circle_rect);
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


        countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
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
        countDownTimer.cancel();
    }

    /**
     * 开始
     *
     * @param view
     */
    public void startPlay(View view) {
        if (!isPlay) {
            countDownTimer.start();
            isPlay = true;
            startActivity(new Intent(MyMainActivity.this, SecondActivity.class));
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
}
