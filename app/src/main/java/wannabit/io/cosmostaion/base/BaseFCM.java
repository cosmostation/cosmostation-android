package wannabit.io.cosmostaion.base;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.dialog.AlertDialogActivity;


public class BaseFCM extends FirebaseMessagingService {

    public static final String PUSH_CHANNEL_NAME = "PushChannelName";
    public static final String PUSH_CHANNEL_ID = "PushChannelId";

    @Override
    public void onNewToken(String token) {
        ((BaseApplication) getApplication()).getBaseDao().setFCMToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String messageBody = remoteMessage.getNotification().getBody();
            String messageTitle = remoteMessage.getNotification().getTitle();
            makeNotification(messageBody, messageTitle, makeAlertIntent(remoteMessage));
        }
    }

    private void makeNotification(String messageBody, String messageTitle, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, new Random(System.currentTimeMillis()).nextInt(), intent, PendingIntent.FLAG_IMMUTABLE);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, PUSH_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(new Random(System.currentTimeMillis()).nextInt(), notificationBuilder.build());
    }

    private Intent makeAlertIntent(RemoteMessage remoteMessage) {
        try {
            Intent intent = new Intent(this, AlertDialogActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("title", remoteMessage.getNotification().getTitle());
            intent.putExtra("body", remoteMessage.getNotification().getBody());
            String url = remoteMessage.getData().get("url");
            intent.putExtra("link", url);
            return intent;
        } catch (Exception e) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("page", 0);
            return intent;
        }
    }
}
