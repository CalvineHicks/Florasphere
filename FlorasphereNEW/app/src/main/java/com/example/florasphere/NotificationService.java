package com.example.florasphere;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.*;


/**
 * Created by Noah on 3/9/2015.
 */
public class NotificationService extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 1;

    public void onReceive(Context context, Intent intent) {
        //Log.i("tag", "oh my");
//        Random random = new Random();
        UserPlantlist plants = UserPlantlist.getInstance(context);
        Plant allPlants[] = plants.toArray();
        for(Plant p : allPlants) {
            int temp = p.getLastWatering();
            temp--;

            if ( temp < 1 ) {
                // BEGIN_INCLUDE(build_action)
                /** Create an intent that will be fired when the user clicks the notification.
                 * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
                 * notification service can fire it on our behalf.
                 */
                Intent nIntent = new Intent(context,
                        FlorasphereListActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nIntent, 0);
                // END_INCLUDE(build_action)

                // BEGIN_INCLUDE (build_notification)
                /**
                 * Use NotificationCompat.Builder to set up our notification.
                 */
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

                /** Set the icon that will appear in the notification bar. This icon also appears
                 * in the lower right hand corner of the notification itself.
                 *
                 * Important note: although you can use any drawable as the small icon, Android
                 * design guidelines state that the icon should be simple and monochrome. Full-color
                 * bitmaps or busy images don't render well on smaller screens and can end up
                 * confusing the user.
                 */
                builder.setSmallIcon(p.getPlantPic());

                // Set the intent that will fire when the user taps the notification.
                builder.setContentIntent(pendingIntent);

                // Set the notification to auto-cancel. This means that the notification will disappear
                // after the user taps it, rather than remaining until it's explicitly dismissed.
                builder.setAutoCancel(true);

                /**
                 *Build the notification's appearance.
                 * Set the large icon, which appears on the left of the notification. In this
                 * sample we'll set the large icon to be the same as our app icon. The app icon is a
                 * reasonable default if you don't have anything more compelling to use as an icon.
                 */
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), p.getPlantPic()));

                /**
                 * Set the text of the notification. This sample sets the three most commononly used
                 * text areas:
                 * 1. The content title, which appears in large type at the top of the notification
                 * 2. The content text, which appears in smaller text below the title
                 * 3. The subtext, which appears under the text on newer devices. Devices running
                 *    versions of Android prior to 4.2 will ignore this field, so don't use it for
                 *    anything vital!
                 */
                builder.setContentTitle("BasicNotifications Sample");
                builder.setContentText("Time to learn about notifications!");
                builder.setSubText("Tap to view documentation about notifications.");

                // END_INCLUDE (build_notification)

                // BEGIN_INCLUDE(send_notification)
                /**
                 * Send the notification. This will immediately display the notification icon in the
                 * notification bar.
                 */
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                        NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
            p.setLastWatering(temp);
        }
    }
}
