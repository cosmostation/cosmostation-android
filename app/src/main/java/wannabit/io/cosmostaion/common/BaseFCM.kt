package wannabit.io.cosmostaion.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.option.notice.PushNotificationActivity
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.Random


class BaseFCM : FirebaseMessagingService() {

    private val PUSH_CHANNEL_NAME = "PushChannelName"
    private val PUSH_CHANNEL_ID = "PushChannelId"

    override fun onNewToken(token: String) {
        Prefs.fcmToken = token
    }

    override fun handleIntent(intent: Intent?) {
        intent?.let {
            var url = ""
            it.extras?.let { bundle ->
                if (bundle.getInt("push_type").toString() == "0") {
                    bundle.getString("txhash")?.let { txHash ->
                        bundle.getString("network")?.let { network ->
                            url = CosmostationConstants.EXPLORER_BASE_TX_URL.replace(
                                "{apiName}", network
                            ).replace("{hash}", txHash)
                        }
                    }
                }

                if (CosmostationApp.instance.appStatus == CosmostationApp.AppStatus.BACKGROUND) {
                    Intent(this, PushNotificationActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        putExtra("url", url)
                        val pendingIntent = PendingIntent.getActivity(
                            this@BaseFCM,
                            Random(System.currentTimeMillis()).nextInt(),
                            this,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                        )

                        val notificationBuilder =
                            NotificationCompat.Builder(this@BaseFCM, PUSH_CHANNEL_ID)
                                .setContentTitle(bundle.getString("gcm.notification.title"))
                                .setContentText(bundle.getString("gcm.notification.body"))
                                .setLargeIcon(
                                    getBitmapFromURL(
                                        bundle.getString("gcm.notification.image").toString()
                                    )
                                ).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher_round)
                                .setContentIntent(pendingIntent)

                        val notificationManager =
                            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            val channel = NotificationChannel(
                                PUSH_CHANNEL_ID,
                                PUSH_CHANNEL_NAME,
                                NotificationManager.IMPORTANCE_HIGH
                            )
                            notificationManager.createNotificationChannel(channel)
                        }

                        Glide.with(this@BaseFCM).asBitmap()
                            .load(bundle.getString("gcm.notification.image"))
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap, transition: Transition<in Bitmap>?
                                ) {
                                    notificationBuilder.setStyle(
                                        NotificationCompat.BigPictureStyle().bigPicture(resource)
                                    )
                                    notificationManager.notify(Random(System.currentTimeMillis()).nextInt(), notificationBuilder.build())
                                }

                                override fun onLoadCleared(placeholder: Drawable?) {}
                            })
                    }

                } else {
                    super.handleIntent(intent)
                }
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            sendNotification(it, remoteMessage.data)
        }
    }

    private fun sendNotification(
        notification: RemoteMessage.Notification, data: Map<String, String>
    ) {
        var url = ""
        if (data["push_type"].toString() == "0") {
            data["txhash"]?.let { txHash ->
                data["network"]?.let { network ->
                    url = CosmostationConstants.EXPLORER_BASE_TX_URL.replace("{apiName}", network)
                        .replace("{hash}", txHash)
                }
            }

            val intent = Intent(this, PushNotificationActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra("url", url)
            }
            val pendingIntent = PendingIntent.getActivity(
                this, Random(System.currentTimeMillis()).nextInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val notificationBuilder = NotificationCompat.Builder(this, PUSH_CHANNEL_ID)
                .setContentTitle(notification.title).setContentText(notification.body)
                .setLargeIcon(getBitmapFromURL(notification.imageUrl.toString()))
                .setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
            }

            Glide.with(this).asBitmap().load(notification.imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap, transition: Transition<in Bitmap>?
                    ) {
                        notificationBuilder.setStyle(
                            NotificationCompat.BigPictureStyle().bigPicture(resource)
                        )
                        notificationManager.notify(Random(System.currentTimeMillis()).nextInt(), notificationBuilder.build())
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
