package com.example.broadcast_receiver;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class OTPActivity extends AppCompatActivity {
    static EditText txtotp;
    int OTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        txtotp = findViewById(R.id.etOTP);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.SEND_SMS)!=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS}, 123);
                }
            }
        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etOTP = findViewById(R.id.etOTP);
                int editOTP = Integer.parseInt(etOTP.getText().toString());
                if (OTP == editOTP) {
                    Toast.makeText(OTPActivity.this, "valid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OTPActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        String phno = getIntent().getStringExtra("mobnum");
        sendSMS(phno);
    }

        public void sendSMS(String mobilenummber){
            Random r =new Random();
            OTP = 10000 + r.nextInt(20000);
            SmsManager smsmgr = SmsManager.getDefault();
            smsmgr.sendTextMessage(mobilenummber,null,"Your OTP is "+OTP,
                    null,null);
        }

        public void putOTP(int otp){ txtotp.setText(otp+" ");}
}