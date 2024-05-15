package com.example.landscapejava;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private Button mButton;
    static final String ORIENTATION_PORTRAIT = "Портретный режим";
    static final String ORIENTATION_LANDSCAPE = "Альбомныйрежим";
    boolean mState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(this::getOrientationOnClick);
        button2.setOnClickListener(this::getRotateOnClick);

        mButton = findViewById(R.id.button6);
        mButton.setText(ORIENTATION_LANDSCAPE);
        mButton.setOnClickListener(this::onClick);


        EditText editText = findViewById(R.id.editTest);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, "onTextChanged: " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getOrientationOnClick(View view) {
        Toast.makeText(this, getScreenOrientation(), Toast.LENGTH_SHORT).show();
    }

    private void getRotateOnClick(View view) {
       Toast.makeText(this, getRotateOrientation(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, ORIENTATION_LANDSCAPE, Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, ORIENTATION_PORTRAIT, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view) {
        if (!mState) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            mButton.setText(ORIENTATION_PORTRAIT);
        }
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mButton.setText(ORIENTATION_LANDSCAPE);
        }
        mState = !mState;
    }

    private String getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return "Портретная ориентация";
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return "Альбомная ориентация";
        else
            return "";
    }

    private String getRotateOrientation() {
        int rotate = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotate) {
            case Surface.ROTATION_0:
                return "Не поворачивали";
            case Surface.ROTATION_90:
                return "Повернули на 90 градусов по часовой стрелке";
            case Surface.ROTATION_180:
                return "Повернули на 180 градусов";
            case Surface.ROTATION_270:
                return "Повернули на 90 градусов против часовой стрелки";
            default:
                return "Не понятно";
        }
    }
}