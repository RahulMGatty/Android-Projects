package com.example.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_main);
    }

    public void handleButton(View v){
        Intent i = new Intent(MainActivity.this,MyMusicService.class);

        if(v.getId() == R.id.btnPlay){
            Toast.makeText(this,"Play",
                    Toast.LENGTH_SHORT).show();
            startService(i);
        }

        if(v.getId() == R.id.btnStop){
            Toast.makeText(this,"Stop",
                    Toast.LENGTH_SHORT).show();
            stopService(i);
        }
    }

}