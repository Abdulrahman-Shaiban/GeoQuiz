package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText edittext;
    RadioGroup radioGroup;
    RadioButton m_radio,f_radio;
    Button start_btn;
    char check = 'm';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_btn = findViewById(R.id.startButton);
        edittext = findViewById(R.id.edit_text);
        m_radio = findViewById(R.id.male_radio);
        f_radio = findViewById(R.id.fmale_radio);
        radioGroup = findViewById(R.id.radio_Group);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edittext.getText().toString().equals("")) {

                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("name",edittext.getText().toString());
                    intent.putExtra("gender",check);
                    startActivity(intent);

                }else
                    Toast.makeText(MainActivity.this, "Enter Your Name And Gender", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.male_radio:
                if (checked)
                    check = 'm';
                    break;
            case R.id.fmale_radio:
                if (checked)
                    check = 'f';
                break;

        }
    }


}
