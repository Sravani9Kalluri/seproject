package com.example.start;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            String msg = "SYN-INR 360.00 debited to a/c No.XX9020on 05-01-2020 for pos txn";
            Databasehelper db = new Databasehelper(context);
            String amount = getamount(msg);
            String shop = getshopname(msg);
            String date = getdate(msg);
            if(amount != "nada" && shop != "nada" && date != "nada"){
                Log.e("addtransaction called","found");
                db.addtransaction(amount,date,shop);}
            //List<List<String>> x;
            //x = db.getCategories();
            //List<String> cat = x.get(0);
            //Toast.makeText(context,cat.get(0),Toast.LENGTH_SHORT).show();
        }


    }
    public String getamount(String msgstr){
        String REGEX = "INR \\d+\\D";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(msgstr);
        if(m.find()){
            Log.e("getamount called","found");
            return(msgstr.substring(m.start()+4,m.end()-1));

        }
        else{
            return "nada";
        }
    }
    public String getshopname(String msgstr){
        Log.e("getshopname called","found");
        return "aishwarya mart";
    }
    public String getdate(String msgstr){
        String REGEX = "on \\d{2}[-/]\\d{2}[-/]\\d+";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(msgstr);
        if(m.find()){
            Log.e("getdate called","found");
            return(msgstr.substring(m.start()+3,m.end()));
        }
        else {
            return "nada";
        }
    }
}
