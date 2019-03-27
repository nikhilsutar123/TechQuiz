package com.example.android.techquiz;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

@TargetApi(19)
public class MainActivity extends AppCompatActivity {

    //AlertDialog.Builder dialog;
    AlertDialog d;
    Boolean isShowingDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            isShowingDialog = savedInstanceState.getBoolean("IS_SHOWING_DIALOG", false);
            if (isShowingDialog) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage(getString(R.string.rule1) + "\n\n" + getString(R.string.rule2) + "\n\n" + getString(R.string.rule3))
                        .setTitle(R.string.rules)
                        .setPositiveButton(R.string.okButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                d = dialog.create();
                d.show();

                isShowingDialog = true;
            }
        }
        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to take user to next screen to play the Quiz
                Intent intent = new Intent(MainActivity.this, QuizLogic.class);
                startActivity(intent);
            }
        });
    }

    //code to hide navbar

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

    @Override
    protected void onPause() {
        super.onPause();
        if (d != null && d.isShowing()) {
            d.dismiss();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("IS_SHOWING_DIALOG", isShowingDialog);
        super.onSaveInstanceState(outState);
    }
}
