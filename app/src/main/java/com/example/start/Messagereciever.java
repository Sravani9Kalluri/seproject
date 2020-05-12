package com.example.start;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;


public class Messagereciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            String msgBody = "";

            if(bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0 ;i < msgs.length;i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msgBody += msgs[i].getMessageBody();
                    }
                    Toast.makeText(context,msgBody,Toast.LENGTH_SHORT).show();
                }catch (Exception e){e.printStackTrace();}
            }
        }


    }
}
