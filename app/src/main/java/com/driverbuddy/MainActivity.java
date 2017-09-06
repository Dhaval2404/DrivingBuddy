package com.driverbuddy;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.driverbuddy.constant.SharedPref;
import com.driverbuddy.util.SharedPreferenceUtil;
import com.driverbuddy.util.TelophonyUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author Dhaval Patel
 * @version 1.0, Sep 6, 2017
 * @since 1.0
 *
 */
@RuntimePermissions
public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean status = SharedPreferenceUtil.getBoolean(this, SharedPref.DRIVING_MODE, false);
        String message = SharedPreferenceUtil.getString(this, SharedPref.DRIVING_MODE_MESSAGE, "");
        EditText mMessage = (EditText) findViewById(R.id.et_message);
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferenceUtil.putString(MainActivity.this, SharedPref.DRIVING_MODE_MESSAGE, String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        SwitchCompat mSwitchCompat = (SwitchCompat) findViewById(R.id.sw_driving_mode);
        mSwitchCompat.setChecked(status);
        mSwitchCompat.setOnCheckedChangeListener(this);
        mMessage.setText(message);

        if(TelophonyUtil.isCallPhonePermitted(this)){
            MainActivityPermissionsDispatcher.showSendSMSWithCheck(this);
        }else{
            MainActivityPermissionsDispatcher.showPhoneCallWithCheck(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferenceUtil.putBoolean(this, SharedPref.DRIVING_MODE, b);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void showPhoneCall() {
        MainActivityPermissionsDispatcher.showSendSMSWithCheck(this);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showRationaleForPhoneCall(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_phone_call_rationale)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void showDeniedForPhoneCall() {
        Toast.makeText(this, R.string.permission_phone_call_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void showNeverAskForPhoneCall() {
        Toast.makeText(this, R.string.permission_phone_call_neverask, Toast.LENGTH_SHORT).show();
    }

    @NeedsPermission(Manifest.permission.SEND_SMS)
    void showSendSMS() {
    }

    @OnShowRationale(Manifest.permission.SEND_SMS)
    void showRationaleForSendSMS(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_send_sms_rationale)
                .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.SEND_SMS)
    void showDeniedForSendSMS() {
        Toast.makeText(this, R.string.permission_send_sms_denied, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.SEND_SMS)
    void showNeverAskForSendSMS() {
        Toast.makeText(this, R.string.permission_send_sms_neverask, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
