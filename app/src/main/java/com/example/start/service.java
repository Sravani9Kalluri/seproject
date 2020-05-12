package com.example.start;


import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class service extends Service {
    private Smsreceiver msmsreceiver;
    private IntentFilter mIntentFilter;

    private class Smsreceiver extends BroadcastReceiver {
        private final String TAG = this.getClass().getSimpleName();


        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                SmsMessage[] msgs;
                String msgBody = "";

                if (bundle != null) {
                    try{

                        Object[] pdus = (Object[]) bundle.get("pdus");
                        msgs = new SmsMessage[pdus.length];
                        for (int i = 0; i < msgs.length; i++) {
                            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                            msgBody += msgs[i].getMessageBody();


                        }
                        Toast.makeText(context,msgBody,Toast.LENGTH_LONG).show();}
                    catch (Exception e){e.printStackTrace();}
                }


            }

        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        msmsreceiver = new Smsreceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(msmsreceiver, mIntentFilter);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }
    @Override
    public void onTaskRemoved(Intent rootIntent){
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msmsreceiver);
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
