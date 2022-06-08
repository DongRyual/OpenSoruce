package com.example.mainview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class flash extends AppCompatActivity {


    private ImageButton toggleButton;
    MediaPlayer player;

    boolean hasCameraFlash = false;
    boolean flashOn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        toggleButton = findViewById(R.id.offbut);

        player = MediaPlayer.create(flash.this, R.raw.button_sound);
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                player.start();     // 버튼이 눌리면 효과음 재생

                // flash가 있는경우
                if(hasCameraFlash){
                    if(flashOn){    // 전등이 켜져있으면 끄고, 버튼 이미지를 off로 변경
                        flashOn = false;
                        toggleButton.setImageResource(R.drawable.switch_off);
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else{   // 전등이 꺼져있으면 전등을 키고 버튼 이미지를 on으로 변경
                        flashOn = true;
                        toggleButton.setImageResource(R.drawable.switch_on);
                        try {
                            flashLightOn();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{   // flash가 없는경우 (가상에뮬레이터에서는 전등을 사용할수 없어 실행시 해당 안내문구가 출력됩니다.)
                    Toast.makeText(flash.this, "No flash available on your device.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // 전등이 켜진다는 안내문구를 출력하며 flash가 켜집니다.
    private void flashLightOn() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = cameraManager.getCameraIdList()[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId, true);
        }
        Toast.makeText(flash.this, "FlashLight is ON 전등켜짐!", Toast.LENGTH_SHORT).show();
    }

    // 전등이 꺼진다는 안내문구를 출력하며 flash가 꺼집니다.
    private void flashLightOff() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId = cameraManager.getCameraIdList()[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId, false);
        }
        Toast.makeText(flash.this, "FlashLight is OFF 전등꺼짐!", Toast.LENGTH_SHORT).show();
    }
}
