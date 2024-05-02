package com.example.simplepaintjava;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;


public class Draw2D extends View {
    private final Paint mPaint = new Paint();
    private final Rect mRect = new Rect();
    private Bitmap mBitmap;

    public Draw2D(Context context) {
        super(context);

        // Выводим значок из ресурсов
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable._0603755_m);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);

        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        // Рисуем желтый круг
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        int radius = 150;
        canvas.drawCircle(width - (radius + 5), radius + 5, radius, mPaint);

        // Рисуем зеленый прямоугольник
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0, height * 0.9f, width, height, mPaint);

        // Рисуем текст
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(52);
        canvas.drawText("Собака на лужайке ", 30, height - 32, mPaint);

        // Текст под углом
        int x = width - 370;
        int y = 390;
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(52);
        String beam = "Лучик солнца!";
        canvas.save();

        // Создаем ограничивающий прямоугольник для наклонного текста поворачиваем холст по центру текста
        canvas.rotate(-45, x + mRect.exactCenterX(), y + mRect.exactCenterY());

        // Рисуем текст
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(beam, x, y, mPaint);

        // восстанавливаем холст
        canvas.save();
        canvas.restore();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            canvas.drawBitmap(mBitmap, width - mBitmap.getWidth(), height - mBitmap.getHeight() - 30, mPaint);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mBitmap = resizeBitmap(mBitmap,800,800);
            canvas.drawBitmap(mBitmap, width - mBitmap.getWidth(), height - mBitmap.getHeight() + 100, mPaint);
        }
    }

    private Bitmap resizeBitmap(Bitmap image, int maxHeight, int maxWidth) {
        if (maxHeight > 0 && maxWidth > 0) {

            int sourceWidth = image.getWidth();
            int sourceHeight = image.getHeight();

            int targetWidth = maxWidth;
            int targetHeight = maxHeight;

            float sourceRatio = sourceWidth / (float)sourceHeight;
            float targetRatio = maxWidth / (float)maxHeight;

            if (targetRatio > sourceRatio) {
                targetWidth = (int)(maxHeight * sourceRatio);
            } else {
                targetHeight = (int)(maxWidth / sourceRatio);
            }

            return Bitmap.createScaledBitmap(image, targetWidth, targetHeight, true);

        }
        throw new RuntimeException();
    }
}