package wannabit.io.cosmostaion.common

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
            val data = remoteMessage.data
            val bundle = Bundle()
            for ((key, value) in data) {
                bundle.putString(key, value)
            }
            makeNotification(notification, bundle, makeAlertIntent(remoteMessage)!!)
        }
    }

    @SuppressLint("RemoteViewLayout")
    private fun makeNotification(
        notification: RemoteMessage.Notification,
        bundle: Bundle,
        intent: Intent
    ) {
        val pendingIntent = PendingIntent.getActivity(
            this, Random(System.currentTimeMillis()).nextInt(), intent, PendingIntent.FLAG_IMMUTABLE
        )
        val title = notification.title
        val body = notification.body
        val image = notification.imageUrl

        val pushType = bundle.getString("push_type")
        val chainId = bundle.getString("chain_id")
        val address = bundle.getString("bech_address")

        val customNotificationView = RemoteViews(packageName, R.layout.item_notification)
        customNotificationView.setTextViewText(R.id.notificationTitle, title)
        customNotificationView.setTextViewText(R.id.notificationText, body)
        customNotificationView.setImageViewUri(R.id.notificationImg, image)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, PUSH_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification).setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent).setCustomContentView(customNotificationView)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        Glide.with(this).asBitmap().load(image).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                notificationBuilder.setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(resource)
                )
                notificationManager.notify(0, notificationBuilder.build())
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

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

        } catch (e: Exception) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.putExtra("page", 0)
        }
    }
}
