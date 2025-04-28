package com.example.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class Second extends AppCompatActivity {

    SharedPreferences preferences;

    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        preferences = getSharedPreferences("mypreference",MODE_PRIVATE);
        editor = preferences.edit();
        if(preferences.getBoolean("theme",false)) {
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
        }else{
            setTheme(android.R.style.ThemeOverlay_Material_Light);
        }
        setContentView(R.layout.activity_second);
    }

}
