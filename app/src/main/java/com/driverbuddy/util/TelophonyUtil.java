package com.driverbuddy.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Dhaval Patel
 * @version 1.0, Sep 6, 2017
 * @since 1.0
 *
 */
public class TelophonyUtil {

    public static boolean isCallPhonePermitted(Context context){
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }
    public static boolean isSendSMSPermitted(Context context){
        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public static void sendMessage(Context context, String mobile, String message){
        if(isSendSMSPermitted(context)) {
            Log.d("SEND_MSG", mobile+">>"+message);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(mobile, null, message, null, null);
        }
    }

    /*
     * Work for device having api less than jelly bean
     */
    public static void disconnectCallOld(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        Class clazz = null;
        try {
            clazz = Class.forName(telephonyManager.getClass().getName());
            Method method = clazz.getDeclaredMethod("getITelephony");
            method.setAccessible(true);
            ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
            telephonyService.endCall();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void disconnectCall(Context context){
        if(!isCallPhonePermitted(context)) {
            return;
        }
        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "FATAL ERROR: could not connect to telephony subsystem");
            Log.e("TAG","Exception object: ", e);
        }
    }

}
