package com.example.android.techquiz;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@TargetApi(19)
public class QuizLogic extends AppCompatActivity {
    int score;

    RadioButton rb1;
    RadioButton rb2;
    String et1, et2;
    CheckBox jpeg, png, tif, spacex, tesla;

    EditText ram;
    EditText bit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);
        onWindowFocusChanged(false);

        Button result = findViewById(R.id.submit);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkScore();
                Toast.makeText(getApplicationContext(), "Your Final score is " + score + " out of 10", Toast.LENGTH_SHORT).show();
                if (rb1.isSelected() || rb2.isSelected() ||
                        spacex.isChecked() || tesla.isChecked() || jpeg.isChecked() || png.isChecked() || tif.isChecked()
                        || ram.getText() != null || bit.getText() != null) {
                    rb1.setChecked(false);
                    rb2.setChecked(false);
                    spacex.toggle();
                    tesla.toggle();
                    jpeg.toggle();
                    png.toggle();
                    tif.toggle();
                    ram.setText("");
                    bit.setText("");

                    et1 = "";
                    et2 = "";

                    score=0;
                }
            }
        });
    }

    public void checkScore() {

        rb1 = findViewById(R.id.steve);
        rb2 = findViewById(R.id.moto);

        spacex = findViewById(R.id.c1);
        tesla = findViewById(R.id.c4);
        jpeg = findViewById(R.id.c21);
        png = findViewById(R.id.c23);
        tif = findViewById(R.id.c24);

        if (rb1.isChecked()) {
            score++;
        }
        if (rb2.isChecked()) {
            score++;
        }
        if (spacex.isChecked() && tesla.isChecked()) {
            score = score + 2;
        } else {
            Toast.makeText(getApplicationContext(), "2 Answers in Q3! You get no points for this question!", Toast.LENGTH_LONG).show();
        }
        if (jpeg.isChecked() && png.isChecked() && tif.isChecked()) {
            score = score + 2;
        } else {
            Toast.makeText(getApplicationContext(), "3 Answers in Q4! You get no points for this question!", Toast.LENGTH_LONG).show();
        }

        ram = findViewById(R.id.edit1);
        bit = findViewById(R.id.edit2);

        et1 = ram.getText().toString();
        et2 = bit.getText().toString();

        if (et1.equalsIgnoreCase("random access memory"))
            score = score + 2;
        if (et2.equals("1") || et2.equalsIgnoreCase("1 byte") || et2.equalsIgnoreCase("one"))
            score = score + 2;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        final View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    }
                });
    }

}
