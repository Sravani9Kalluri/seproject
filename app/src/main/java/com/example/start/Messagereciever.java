package com.example.start;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import java.lang.String;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;


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
            Databasehelper db = new Databasehelper(context);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            int p_month = sp.getInt("com.start.month",0);
            Calendar now = Calendar.getInstance();
            if(p_month != now.get(Calendar.MONTH)+1){
                Log.e("init","start");
                db.initamount();
            }

            //String msg = "SYN-INR 360.00 debited to a/c No.XX9020on 05-01-2020 for pos txn";

            String amount = getamount(msgBody);
            String shop = getshopname(msgBody);
            String date = getdate(msgBody);
            if(amount != "nada" && shop != "nada" && date != "nada"){
                Log.e("addtransaction called","found");
                if(db.addtransaction(amount,date,shop)){
                    Toast.makeText(context,"limit exceeded",Toast.LENGTH_SHORT).show();
                }
            }

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
        String REGEX1 = "for POS txn";
        Pattern p1 = Pattern.compile(REGEX1);
        Matcher m1 = p1.matcher(msgstr);
        String REGEX2 = "for ATM txn";
        Pattern p2 = Pattern.compile(REGEX2);
        Matcher m2 = p2.matcher(msgstr);
        Log.e("getshopname called","found");
        if(m1.find()){
          String msgstrn = msgstr.substring(m1.start());
          String REGEX3 = "-.+Avl";
          Pattern p3 = Pattern.compile(REGEX3);
          Matcher m3 = p3.matcher(msgstrn);
          if(m3.find()){
              return(msgstrn.substring(m3.start()+2,m3.end()-4).toLowerCase());
          }



        }
        if(m2.find()){
            return "atm";
        }
        return "nada";
    }
    public String getdate(String msgstr){
        String REGEX = "on \\d{2}[-/]\\d{2}[-/]\\d+";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(msgstr);
        if(m.find()){
            Log.e("getdate called","found");
            String datestr = msgstr.substring(m.start()+3,m.end()).replace('-','/');
            String [] datest= datestr.split("/");
            return(datest[2] + "/" + datest[1] + "/" + datest[0]);
        }
        else {
            return "nada";
        }
    }
}
