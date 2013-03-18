package br.edu.ufcg.util;

import android.app.Activity;
import android.app.AlertDialog;
import br.edu.ufcg.fitnessmanagement.R;

public class Utils {
	public static void showMessage(Activity activity, Message msg, String title, String message){
		AlertDialog.Builder caixa = new AlertDialog.Builder(activity);		
		caixa.setTitle(title);
		caixa.setMessage(message);

		if(msg == Message.CONFIRM)
			caixa.setIcon(R.drawable.ok);
		
		else if(msg == Message.ALERT)
			caixa.setIcon(R.drawable.alert);
		
		else if(msg == Message.ERROR)
			caixa.setIcon(R.drawable.error);
		
		caixa.setPositiveButton("OK", null);
		caixa.show();
	}
}