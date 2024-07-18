package com.hsr2024.ex41broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// 안드로이드의 4대 컴포넌트는 AndroidManifest.xml에 등록해야 사용이 가능함
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "방송 수신했습니다.", Toast.LENGTH_SHORT).show();
    }
}
