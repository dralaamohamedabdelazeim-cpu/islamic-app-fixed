package com.islamic.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;

public class PrayerAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String prayerName = intent.getStringExtra("prayer_name");
        int notifId = intent.getIntExtra("notif_id", 0);

        if (prayerName == null) prayerName = "الصلاة";

        // Open app intent
        Intent openApp = new Intent(context, MainActivity.class);
        openApp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi = PendingIntent.getActivity(
            context, 0, openApp,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("🕌 حان وقت صلاة " + prayerName)
            .setContentText("الله أكبر الله أكبر، حي على الصلاة")
            .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("اللهُ أكبرُ اللهُ أكبرُ\nحيَّ على الصلاةِ\nحيَّ على الفلاحِ"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(pi)
            .setVibrate(new long[]{0, 300, 100, 300, 100, 300});

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(notifId, builder.build());
        }

        // Vibrate device
        android.os.Vibrator v = (android.os.Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(android.os.VibrationEffect.createWaveform(new long[]{0, 500, 200, 500}, -1));
            } else {
                v.vibrate(new long[]{0, 500, 200, 500}, -1);
            }
        }
    }
}
