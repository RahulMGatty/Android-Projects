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

public class MainActivity extends AppCompatActivity {

    Button btn_Dark,btn_Light,btn_Second;

    static int themeId=
            android.R.style.ThemeOverlay_Material_Light;

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
        setContentView(R.layout.activity_main);
    }

    public void findTheme (View v) {

        if (R.id.btn_Dark == v.getId()) {
            editor.putBoolean("theme", true).apply();
            recreate();
        } else if (R.id.btn_Light == v.getId()) {
            editor.putBoolean("theme", true).apply();
            recreate();
        } else if (R.id.btn_Second == v.getId()) {
            Intent i = new Intent(MainActivity.this, Second.class);
            startActivity(i);
        }
    }
}