package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    LinearLayout hiddenLayout ,rw_hiddenLayout ;
    ImageView imageView;
    TextView tv_question,tv_score,tv_name,tv_right,tv_wrong;
    Button t_btn,f_btn,first_btn,second_btn,third_btn,cheate_btn;
    int q_Index = 0;
    int score = 0;
    int right;
    int wrong;
    int cheat;


    public Questions[] q = new Questions[]{

            new Questions(R.string.question_1_1,false,"geo","easy"),
            new Questions(R.string.question_1_2,true,"geo","easy"),
            new Questions(R.string.question_1_3,false,"geo","easy"),
            new Questions(R.string.question_1_4,false,"geo","easy"),
            new Questions(R.string.question_1_5,true,"geo","half"),
            new Questions(R.string.question_1_6,false,"geo","half"),
            new Questions(R.string.question_1_7,false,"geo","half"),
            new Questions(R.string.question_1_8,true,"geo","half"),
            new Questions(R.string.question_1_9,false,"geo","hard"),
            new Questions(R.string.question_1_10,true,"geo","hard"),
            new Questions(R.string.question_1_11,true,"geo","hard"),
            new Questions(R.string.question_1_11,false,"geo","hard"),

            new Questions(R.string.question_2_1,true,"sport","easy"),
            new Questions(R.string.question_2_2,true,"sport","easy"),
            new Questions(R.string.question_2_3,false,"sport","easy"),
            new Questions(R.string.question_2_4,true,"sport","easy"),
            new Questions(R.string.question_2_5,false,"sport","half"),
            new Questions(R.string.question_2_6,true,"sport","half"),
            new Questions(R.string.question_2_7,false,"sport","half"),
            new Questions(R.string.question_2_8,false,"sport","half"),
            new Questions(R.string.question_2_9,true,"sport","hard"),
            new Questions(R.string.question_2_10,false,"sport","hard"),
            new Questions(R.string.question_2_11,false,"sport","hard"),
            new Questions(R.string.question_2_12,true,"sport","hard"),


            new Questions(R.string.question_3_1,true,"android","easy"),
            new Questions(R.string.question_3_2,true,"android","easy"),
            new Questions(R.string.question_3_3,false,"android","easy"),
            new Questions(R.string.question_3_4,false,"android","easy"),
            new Questions(R.string.question_3_5,true,"android","half"),
            new Questions(R.string.question_3_6,false,"android","half"),
            new Questions(R.string.question_3_7,false,"android","half"),
            new Questions(R.string.question_3_8,false,"android","half"),
            new Questions(R.string.question_3_9,false,"android","hard"),
            new Questions(R.string.question_3_10,false,"android","hard"),
            new Questions(R.string.question_3_11,true,"android","hard"),
            new Questions(R.string.question_3_12,true,"android","hard")

    };

    ArrayList<Questions> question = new ArrayList();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            startActivity(new Intent(QuizActivity.this, MainActivity.class));
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create Status", "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);


        tv_question = findViewById(R.id.tv_question);
        tv_score = findViewById(R.id.tv_score);
        t_btn = findViewById(R.id.t_btn);
        f_btn = findViewById(R.id.f_btn);
        first_btn = findViewById(R.id.first_btn);
        second_btn = findViewById(R.id.second_btn);
        third_btn = findViewById(R.id.third_btn);
        hiddenLayout = findViewById(R.id.hidden_Layout);
        rw_hiddenLayout = findViewById(R.id.rw_layout);
        imageView = findViewById(R.id.profile_image);
        tv_name = findViewById(R.id.name);
        cheate_btn = findViewById(R.id.cheat_btn);
        tv_right = findViewById(R.id.right_a);
        tv_wrong = findViewById(R.id.wrong);


        hiddenLayout.setVisibility(View.INVISIBLE);
        rw_hiddenLayout.setVisibility(View.INVISIBLE);

        char c='m';
        String name = getIntent().getStringExtra("name");
        char gender = getIntent().getCharExtra("gender",c);

        tv_name.setText(name);

        if (gender == 'm') {
            imageView.setImageDrawable(getDrawable(R.drawable.men));

        } else if(gender == 'f') {
            imageView.setImageDrawable(getDrawable(R.drawable.women));
        }



        if(savedInstanceState != null){

            int visibility;
            visibility = savedInstanceState.getInt("visibility");

            if (visibility == View.VISIBLE){
                hiddenLayout.setVisibility(View.VISIBLE);
                rw_hiddenLayout.setVisibility(View.VISIBLE);
            }else{
                hiddenLayout.setVisibility(View.INVISIBLE);
                rw_hiddenLayout.setVisibility(View.INVISIBLE);
            }

            q_Index =  savedInstanceState.getInt("index");
            score =  savedInstanceState.getInt("score");
            tv_score.setText(savedInstanceState.getString("tv_score"));

            question = (ArrayList<Questions>) savedInstanceState.getSerializable("tv_question");
            if(question.size()>0) {
                updateQuestion();
                updateScore(score);
            }

        }



        t_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                putIntent();

            }
        });


        f_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
                putIntent();

            }
        });


        cheate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean s = question.get(q_Index).isAnswer();
                Toast.makeText(QuizActivity.this, s + "", Toast.LENGTH_LONG).show();
                cheat++;

            }
        });


        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hiddenLayout.setVisibility(View.VISIBLE);
                rw_hiddenLayout.setVisibility(View.VISIBLE);
                first_btn.setVisibility(View.INVISIBLE);
                second_btn.setVisibility(View.INVISIBLE);
                third_btn.setVisibility(View.INVISIBLE);

                fillArrayList("geo");
                updateQuestion();

            }
        });


        second_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hiddenLayout.setVisibility(View.VISIBLE);
                rw_hiddenLayout.setVisibility(View.VISIBLE);
                first_btn.setVisibility(View.INVISIBLE);
                second_btn.setVisibility(View.INVISIBLE);
                third_btn.setVisibility(View.INVISIBLE);

                fillArrayList("sport");
                updateQuestion();

            }
        });

        third_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hiddenLayout.setVisibility(View.VISIBLE);
                rw_hiddenLayout.setVisibility(View.VISIBLE);
                first_btn.setVisibility(View.INVISIBLE);
                second_btn.setVisibility(View.INVISIBLE);
                third_btn.setVisibility(View.INVISIBLE);

                fillArrayList("android");
                updateQuestion();

            }
        });

        if(hiddenLayout.getVisibility() == View.INVISIBLE) {
            question.clear();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("tv_question",question);
        outState.putString("tv_score",tv_score.getText() + "");
        outState.putInt("score",score);
        outState.putInt("index",q_Index);
        outState.putInt("visibility",hiddenLayout.getVisibility());

    }

    private void updateQuestion() {

        tv_question.setText(question.get(q_Index).getQuestionId());

    }


    private void checkAnswer(boolean pressedTrue) {

        boolean answerTrue = question.get(q_Index).isAnswer();
        int messageResId = 0;
        if (pressedTrue == answerTrue) {
            messageResId = R.string.correct_answer;
            right++;
            switch (question.get(q_Index).getType()) {
                case "easy" :
                    if (cheat == 1){

                    } else
                    score++;
                    cheat = 0;
                    break;

                case "half" :
                    if (cheat == 1 ){

                    } else
                    score+= 2;
                    cheat = 0;
                    break;

                case "hard" :
                    if (cheat == 1){

                    } else
                    score+= 3;
                    cheat = 0;
                    break;
            }
        } else {
            messageResId = R.string.incorrect_answer;
            wrong++;
        }

    }

    private void updateScore(int point) {

        tv_score.setText("" + score);
        tv_right.setText("" + right);
        tv_wrong.setText("" + wrong);

    }

    private void fillArrayList(String qType){

        Random r = new Random();

        for(int x=0; x<q.length; x++) {
            int a = r.nextInt(35 - 0) + 0;

            if (q[a].getCategory().equals(qType) && q[a].getType().equals("easy")) {

                for(int z=0; z<1; z++) {
                if(question.isEmpty()) {
                    question.add(q[a]);
                }

            }
            }
        }

        for(int x=0; x<q.length; x++) {
            int a = r.nextInt(35 - 0) + 0;

            if (q[a].getCategory().equals(qType) && q[a].getType().equals("half")) {

                for(int z=0; z<1; z++) {
                    if(question.size() == 1) {
                        question.add(q[a]);
                    }

                }
            }
        }

        for(int x=0; x<q.length; x++) {
            int a = r.nextInt(35 - 0) + 0;

            if (q[a].getCategory().equals(qType) && q[a].getType().equals("hard")) {

                for(int z=0; z<1; z++) {
                    if(question.size() == 2) {
                        question.add(q[a]);
                    }

                }
            }
        }

        for(int x=0; x<q.length; x++) {
            int a = r.nextInt(35 - 0) + 0;

            if (q[a].getCategory().equals(qType) && q[a].getType().equals("hard")) {

                for(int z=0; z<1; z++) {
                    if(question.size() == 3) {
                        question.add(q[a]);
                    }

                }
            }
        }


    }


    private void putIntent(){

        updateScore(score);

        q_Index = (q_Index + 1) % question.size();

        if(q_Index >= question.size()-1){

            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("finalScore",score);
            intent.putExtra("right",right);
            intent.putExtra("wrong",wrong);
            intent.putExtra("fullscore",question.size());
            startActivity(intent);

        } else {

            updateQuestion();

        }

    }


}
