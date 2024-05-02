package com.example.sunrisejava;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получим ссылку на солнце
        ImageView sunImageView = findViewById(R.id.sun);

        // Анимация для восхода солнца
        Animation sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise);

        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation);

        // Получим ссылку на часы
        ImageView clockImageView = findViewById(R.id.clock);

        // анимация для вращения часов
        Animation clockTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.clock_turn);
        clockImageView.startAnimation(clockTurnAnimation);

        // получим ссылку на часовую стрелку
        ImageView hourImageView = findViewById(R.id.hour_hand);

        // анимация для стрелки
        Animation hourTurnAnimation = AnimationUtils.loadAnimation(this, R.anim.hour_turn);

        // присоединяем анимацию
        hourImageView.startAnimation(hourTurnAnimation);


        // Получим ссылку на собаку
        ImageView godImageView = findViewById(R.id.dog);

        // Анимация прибегающей собаки
        Animation dogRunAnimation = AnimationUtils.loadAnimation(this, R.anim.dog_run);

        // Подключаем анимацию к нужному View
        godImageView.startAnimation(dogRunAnimation);
    }
}