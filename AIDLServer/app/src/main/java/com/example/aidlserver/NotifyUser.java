package com.example.aidlserver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import static java.lang.Boolean.TRUE;

public class NotifyUser extends BroadcastReceiver {
    public Context context;
    private SmsDatabaseHelper smsDatabaseHelper;
    String recepientName;
    int SmsID;

    @Override
    public void onReceive(Context context, Intent intent) {
        smsDatabaseHelper = new SmsDatabaseHelper(context);

        // Receive SmsID of the alarm to update the SMS status in database, Receive recepient name to display in notifications
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            recepientName = bundle.getString("name");
            SmsID = bundle.getInt("SmsID");
        }

        String messageSentStatus = "Message could not be sent.\nUnknown Error";
        setContext(context);
        // Get result code, update database and set notifications message
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                messageSentStatus = "Message has been sent.";
                updateSmsToSent();
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                messageSentStatus = "Message could not be sent.\nGeneric Failure Error";
                updateSmsToFailed();
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                messageSentStatus = "Message could not be sent.\nNo Service Available";
                updateSmsToFailed();
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                messageSentStatus = "Message could not be sent.\nNull PDU";
                updateSmsToFailed();
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                messageSentStatus = "Message could not be sent.\nRadio is off";
                updateSmsToFailed();
                break;
            default:
                break;
        }

        // Access shared preferences to determine if notification should be sent.
        SharedPreferences NotificationsPref = getContext().getSharedPreferences("switchStaus", 0);
        boolean notifcationsOn = NotificationsPref.getBoolean("notificationSwitch", true);

        // If user would like to receive notifications send notification with appropriate message
        if (notifcationsOn == TRUE) {
            sendNotification(messageSentStatus);
        }
    }

    public void updateSmsToSent() {
        smsDatabaseHelper.updateSmsToSent(SmsID);
    }

    public void updateSmsToFailed() {
        smsDatabaseHelper.updateSmsToFailed(SmsID);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // Build notification using helper class
    public void sendNotification(String notificationMessage) {
        NotificationHelper notificationHelper = new NotificationHelper(getContext());
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(notificationMessage, recepientName);
        notificationHelper.getManager().notify(1, nb.build());

    }
}