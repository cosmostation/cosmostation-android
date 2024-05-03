package wannabit.io.cosmostaion.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.main.setting.general.PushDialogActivity
import java.util.Random

class BaseFCM : FirebaseMessagingService() {

    private val PUSH_CHANNEL_NAME = "PushChannelName"
    private val PUSH_CHANNEL_ID = "PushChannelId"

    override fun onNewToken(token: String) {
        Prefs.fcmToken = token
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { notification ->
            val messageTitle = notification.title
            val messageBody = notification.body
            makeNotification(messageBody, messageTitle, makeAlertIntent(remoteMessage)!!)
        }
    }

    private fun makeNotification(messageBody: String?, messageTitle: String?, intent: Intent) {
        val pendingIntent = PendingIntent.getActivity(
            this, Random(System.currentTimeMillis()).nextInt(), intent, PendingIntent.FLAG_IMMUTABLE
        )

        val customNotificationView = RemoteViews(packageName, R.layout.item_notification)
        customNotificationView.setTextViewText(R.id.notificationTitle, messageTitle)
        customNotificationView.setTextViewText(R.id.notificationText, messageBody)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, PUSH_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification).setContentTitle(messageTitle)
                .setContentText(messageBody).setAutoCancel(true).setSound(defaultSoundUri)
                .setContentIntent(pendingIntent).setCustomContentView(customNotificationView)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(
            Random(System.currentTimeMillis()).nextInt(), notificationBuilder.build()
        )
    }

    private fun makeAlertIntent(remoteMessage: RemoteMessage): Intent? {
        return try {
            val intent = Intent(this, PushDialogActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("title", remoteMessage.notification!!.title)
            intent.putExtra("body", remoteMessage.notification!!.body)
            val url = remoteMessage.data["url"]
            intent.putExtra("link", url)
            intent

        } catch (e: Exception) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.putExtra("page", 0)
            intent
        }
    }
}
