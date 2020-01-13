package com.example.geoquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    ImageView imageView;
    TextView mGrade, mFinalScore,tv_right,tv_wrong;
    Button mRetryButton;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {

            startActivity(new Intent(ResultActivity.this, MainActivity.class));
            ResultActivity.this.finish();
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiity_result);

        mGrade = findViewById(R.id.grade);
        mFinalScore = findViewById(R.id.outOf);
        mRetryButton = findViewById(R.id.retry);
        tv_right = findViewById(R.id.right_A);
        tv_wrong = findViewById(R.id.wrong_A);
        imageView = findViewById(R.id.image_view);

        imageView.setVisibility(View.INVISIBLE);

        int score = getIntent().getExtras().getInt("finalScore");
        int right = getIntent().getExtras().getInt("right");
        int wrong = getIntent().getExtras().getInt("wrong");
//        int fullscore = getIntent().getExtras().getInt("fullscore");
        int fullscore = 6;



        mFinalScore.setText("You scored " + score + " out of " + fullscore);
        tv_right.setText("" + right);
        tv_wrong.setText("" + wrong);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.clapping);


        if (score == 6){
            mGrade.setText("Outstanding");
            imageView.setVisibility(View.VISIBLE);
            mp.start();
        }else if (score == 5){
            mGrade.setText("Good Work");
        }else if (score == 4) {
            mGrade.setText("Good Effort");
        }else {
            mGrade.setText("Go over your notes");
        }


        mRetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                ResultActivity.this.finish();
            }
        });

    }

}
