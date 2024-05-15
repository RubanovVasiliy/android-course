package com.example.toastjava;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClick(View view) {
        Toast toast = Toast.makeText(this, R.string.learn_java, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, -400);
        LinearLayout toastContainer = (LinearLayout) toast.getView();
        ImageView javaImageView = new ImageView(getApplicationContext());
        javaImageView.setImageResource(R.drawable.java_label);
        toastContainer.addView(javaImageView, 0);
        toast.show();
    }
}