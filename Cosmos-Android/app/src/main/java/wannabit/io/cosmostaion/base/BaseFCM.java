package wannabit.io.cosmostaion.base;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_SHOWLOG;

public class BaseFCM extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        if (IS_SHOWLOG) { WLog.w("Refreshed token: " + token); };
        ((BaseApplication)getApplication()).getBaseDao().setFCMToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (IS_SHOWLOG) { WLog.w("From: " + remoteMessage.getFrom()); }

        if (remoteMessage.getData().size() > 0) {
            if (IS_SHOWLOG) { WLog.w("Message data payload: " + remoteMessage.getData()); }

        }

        if (remoteMessage.getNotification() != null) {
            if(remoteMessage.getNotification().getTitle() != null)
                if (IS_SHOWLOG) { WLog.w("Message Notification title: " + remoteMessage.getNotification().getTitle()); }
            if(remoteMessage.getNotification().getBody() != null)
                if (IS_SHOWLOG) {WLog.w("Message Notification Body: " + remoteMessage.getNotification().getBody()); }
        }

        if (remoteMessage.getData().get("notifyto") != null &&
                remoteMessage.getData().get("txid") != null &&
                remoteMessage.getData().get("type") != null &&
                remoteMessage.getNotification() != null &&
                remoteMessage.getNotification().getTitle() != null &&
                remoteMessage.getNotification().getBody() != null) {
            Intent intent = new Intent("pushAlarm");
            intent.putExtra("pushNotifyto", remoteMessage.getData().get("notifyto"));
            intent.putExtra("txid", remoteMessage.getData().get("txid"));
            intent.putExtra("type", remoteMessage.getData().get("type"));
            intent.putExtra("title", remoteMessage.getNotification().getTitle());
            intent.putExtra("Body", remoteMessage.getNotification().getBody());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        }
    }


}
