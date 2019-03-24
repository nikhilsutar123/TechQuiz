package com.example.android.techquiz;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@TargetApi(19)
public class QuizLogic extends AppCompatActivity {
     int score;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_screen);
        onWindowFocusChanged(false);

        RadioGroup rg1 = findViewById(R.id.radioGroup_1);
        final RadioButton rb1 = findViewById(R.id.steve);
               rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if (rb1.isChecked()){
                   score++;
               }

           }
       });
    }

    public void seeScore(View view) {
        Toast.makeText(getApplicationContext(), "Your final score is "+score+ " out of 10", Toast.LENGTH_LONG).show();
    }

    public void immersiveMode() {
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        immersiveMode();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        immersiveMode();
                    }
                });
    }

}
