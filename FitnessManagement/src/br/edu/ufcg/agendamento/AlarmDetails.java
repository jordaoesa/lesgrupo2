package br.edu.ufcg.agendamento;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
 
public class AlarmDetails extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.alarmdetails);
 
        NotificationManager nm = (NotificationManager) 
            getSystemService(NOTIFICATION_SERVICE);
 
        nm.cancel(getIntent().getExtras().getInt("NotifID"));        
    }
}
