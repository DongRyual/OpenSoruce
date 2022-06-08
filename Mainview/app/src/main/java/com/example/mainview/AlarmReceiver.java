package com.example.mainview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent sIntent = new Intent(context, AlarmService.class);

        //FOREGROUND 에서 실행
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(sIntent);
        } else {
            context.startService(sIntent);
        }
    }
}