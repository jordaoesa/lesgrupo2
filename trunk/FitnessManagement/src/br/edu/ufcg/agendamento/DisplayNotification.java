package br.edu.ufcg.agendamento;

import br.edu.ufcg.fitnessmanagement.MainActivity;
import br.edu.ufcg.fitnessmanagement.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
 
public class DisplayNotification extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        int notifID = getIntent().getExtras().getInt("NotifID");
// 
//        Intent i = new Intent("br.edu.ufcg.fitnessmanagement.MAinActivity");
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        i.putExtra("NotifID", notifID);  
// 
//        PendingIntent detailsIntent = 
//            PendingIntent.getActivity(this, 0, i, 0);
//
//        NotificationManager nm = (NotificationManager)
//            getSystemService(NOTIFICATION_SERVICE);
//        
//        Notification notif = new Notification(
//            R.drawable.icon, 
//            "Aula!",
//            System.currentTimeMillis());
//        CharSequence from = "Hora do treino!";
//        CharSequence message = "Seja pontual com seus alunos.";
//        notif.setLatestEventInfo(this, from, message, detailsIntent);
// 
//        notif.vibrate = new long[] { 100, 250, 100, 500};        
//        nm.notify(notifID, notif);
//        finish();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("Horário da Aula.")
                .setContentText("");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent("br.edu.ufcg.fitnessmanagement.MAinActivity");

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(notifID, mBuilder.build());
    }
}