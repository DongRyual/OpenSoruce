package com.example.mainview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cal:
                Intent intent1 = new Intent(getApplicationContext(), calculator.class);
                startActivity(intent1);
                break;
            case R.id.rcp:
                Intent intent2 = new Intent(getApplicationContext(), game.class);
                startActivity(intent2);
                break;
            case R.id.stp:
                Intent intent3 = new Intent(getApplicationContext(), stop.class);
                startActivity(intent3);
                break;
            case R.id.light:
                Intent intent4 = new Intent(getApplicationContext(), flash.class);
                startActivity(intent4);
                break;
            case R.id.alr:
                Intent intent5 = new Intent(getApplicationContext(), alarm.class);
                startActivity(intent5);
                break;

        }
    }
}