package com.emreatak.android.RadRoid.music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.emreatak.android.RadRoid.activities.MainActivity;

/**
 * Created by Cenk on 30/11/2016.
 */

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}