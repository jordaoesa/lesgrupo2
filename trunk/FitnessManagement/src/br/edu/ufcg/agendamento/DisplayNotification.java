package br.edu.ufcg.agendamento;

import br.edu.ufcg.aluno.Aluno;
import br.edu.ufcg.fitnessmanagement.R;
import br.edu.ufcg.util.FitnessManagementSingleton;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
 
public class DisplayNotification extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        int notifID = getIntent().getExtras().getInt("NotifID");
        Intent i = new Intent("br.edu.ufcg.fitnessmanagement.MainActivity");
        i.putExtra("NotifID", notifID);  
 
        Aluno aluno = FitnessManagementSingleton.getAlunoFachadaInstance().getAlunoFromId(notifID);
        PendingIntent detailsIntent = 
            PendingIntent.getActivity(this, 0, i, 0);
 
        NotificationManager nm = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification(
            R.drawable.icon, 
            "Aula!",
            System.currentTimeMillis());
        notif.flags|=Notification.FLAG_AUTO_CANCEL;
        CharSequence from = "Horário de treino.";
        CharSequence message = "Aluno:" + aluno.getNome();        
        notif.setLatestEventInfo(this, from, message, detailsIntent);
        notif.vibrate = new long[] { 100, 250, 100, 500};        
        nm.notify(notifID, notif);
        
        finish();
    }
}