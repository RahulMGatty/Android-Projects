package com.example.externalstorage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "exampleExternal.txt";
    EditText editTextExternal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editTextExternal = findViewById(R.id.Edit_Text_External);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    } ,123);
        }
    }

    public void saveDataExternal(View view){
        File folder = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS).getPath() + "/AIMIT");
        if(!folder.exists()){
            folder.mkdir();
        }

        File myFile = new File(folder, FILE_NAME);
        String text = editTextExternal.getText().toString();
        FileOutputStream fos =null;
        try{
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(text.getBytes());
            editTextExternal.getText().clear();
            Toast.makeText(this, "Saved to:"+getFilesDir()+"/"+FILE_NAME,
                    Toast.LENGTH_SHORT).show();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            }  catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void loadDataExternal(View view){
        FileInputStream fis = null;
        try{
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine())!=null){
                sb.append(text).append("\n");
            }
            editTextExternal.setText(sb.toString());
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e){
            e.printStackTrace();
        }  finally {
            if(fis !=null){
                try{
                    fis.close();
                }   catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}