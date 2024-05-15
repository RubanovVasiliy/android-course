package com.example.basicactivityjava;

import android.content.Context;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.Snackbar;

class SnackbarHelper {

    static void configSnackbar(Context context, Snackbar snackbar) {
        addMargins(snackbar);
        setRoundBordersBg(context, snackbar);
        ViewCompat.setElevation(snackbar.getView(), 6f);
    }

    private static void addMargins(Snackbar snack) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snack.getView().getLayoutParams();
        params.setMargins(12, 12, 12, 12);
        snack.getView().setLayoutParams(params);
    }

    private static void setRoundBordersBg(Context context, Snackbar snackbar) {
        // API 21
        snackbar.getView().setBackground(context.getDrawable(R.drawable.bg_snackbar));
    }
}