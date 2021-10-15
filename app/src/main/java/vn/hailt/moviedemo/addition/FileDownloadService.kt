package vn.hailt.moviedemo.addition

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import vn.hailt.moviedemo.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class FileDownloadService : IntentService("FileDownLoad") {

    companion object {
        const val TAG = "TAG"
        const val NOTIFICATION_ID_FOREGROUND = 1410
        const val NOTIFICATION_ID = 1014
        const val NOTIFICATION_CHANNEL = "1410"
    }

    private lateinit var nm: NotificationManager

    override fun onHandleIntent(intent: Intent?) {
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val url: URL?
        val uri: Uri? = null
        val file: File

        val extras = intent?.extras
        if (extras != null) {
            url = extras["URL"] as URL?

            val parent: File =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val fileName = extras.getString("FILE") ?: "temp_${System.currentTimeMillis()}"
            file = File(parent, fileName)

        } else {
            showNotification(-1, "Error, No file to download", null)
            return
        }

        try {
//            val notificationBuilder =
//                NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setWhen(System.currentTimeMillis())
//                    .setContentTitle("File Download")
//                    .setContentText("Downloading")
//                    .setProgress(100, 0, true)
//                    .setAutoCancel(false)
//
//            startForeground(NOTIFICATION_ID_FOREGROUND, notificationBuilder.build())

            val urlConnection = url!!.openConnection() as HttpURLConnection

            val fos: FileOutputStream = if (uri == null) {
                FileOutputStream(file)
            } else {
                val fileDescriptor = contentResolver.openFileDescriptor(uri, "w")
                FileOutputStream(fileDescriptor!!.fileDescriptor)
            }
            try {

                val inputStream = urlConnection.inputStream
                val buffer = ByteArray(4096)
                var n: Int
                val contentLength = urlConnection.contentLength
                var total: Long = 0

                while (inputStream.read(buffer).also { n = it } != -1) {
                    Log.d(TAG, "onHandleIntent: download $n bytes")
                    total += n
                    fos.write(buffer, 0, n)

                    val progress = (total * 100 / contentLength).toInt()

//                    notificationBuilder.setProgress(100, progress, false)
//                    nm.notify(NOTIFICATION_ID, notificationBuilder.build())

                    Intent("ACTION_UPDATE_PROGRESS").also { progressIntent ->
                        progressIntent.putExtra("PROGRESS", progress)
                        LocalBroadcastManager.getInstance(this).sendBroadcast(progressIntent)
                    }
                }
            } finally {
                urlConnection.disconnect()
                fos.close()
            }

//            if (uri == null) {
//                val values = ContentValues()
//                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
//                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//                values.put(MediaStore.MediaColumns.DATA, file.toString())
//                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//            }

            showNotification(0, "Download finished", file)

        } catch (e: IOException) {
            showNotification(-2, "Error: $e", null)
        }
        stopSelf()
    }

    private fun showNotification(value: Int, message: String?, file: File?) {
        val notification: Notification
        if (value >= 0) {
            val notificationIntent = Intent()
            notificationIntent.action = Intent.ACTION_VIEW
            notificationIntent.setDataAndType(
                Uri.parse("file://" + file?.absolutePath), "image/*"
            )
            val contentIntent = PendingIntent.getActivity(this, 100, notificationIntent, 0)
            notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis()) //When the event occurred, now, since noti are stored by time.
                .setContentIntent(contentIntent) //what activity to open.
                .setContentTitle("File Download") //Title message top row.
                .setContentText(message) //message when looking at the notification, second row
                .setAutoCancel(true) //allow auto cancel when pressed.
                .build() //finally build and return a Notification.
        } else {
            notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis()) //When the event occurred, now, since noti are stored by time.
                .setContentTitle("File Download") //Title message top row.
                .setContentText(message) //message when looking at the notification, second row
                .setAutoCancel(true) //allow auto cancel when pressed.
                .build() //finally build and return a Notification.
        }
        //finally Show the notification
        nm.notify(NOTIFICATION_ID, notification)
    }

}