package com.driverbuddy.receiver;

/**
 * Created by dhaval on 26/8/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.driverbuddy.constant.SharedPref;
import com.driverbuddy.util.SharedPreferenceUtil;
import com.driverbuddy.util.TelophonyUtil;

/**
 * @author Dhaval Patel
 * @version 1.0, Sep 6, 2017
 * @since 1.0
 *
 */
public class PhonecallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            Log.d("TAG", "Outgoing call");
        } else{
            Log.d("TAG", "Incoming call");
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(stateStr!=null && stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                boolean status = SharedPreferenceUtil.getBoolean(context, SharedPref.DRIVING_MODE, false);
                if(status) {
                    disconnectCall(context);
                    sendMessage(context, number);
                }
            }
        }
    }

    private void disconnectCall(Context context) {
        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            TelophonyUtil.disconnectCallOld(context);
        }else{
            TelophonyUtil.disconnectCall(context);
        }
    }

    private void sendMessage(Context context, String number){
        String message = SharedPreferenceUtil.getString(context, SharedPref.DRIVING_MODE_MESSAGE, "");
        long time = SharedPreferenceUtil.getLong(context, "LAST_SEND_SMS", 0);
        if(!TextUtils.isEmpty(message) && (System.currentTimeMillis()-time > 3000)){
            SharedPreferenceUtil.putLong(context, "LAST_SEND_SMS", System.currentTimeMillis());
            TelophonyUtil.sendMessage(context, number, message);
        }
    }

}