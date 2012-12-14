package com.ufcg.util;

import com.ufcg.R;

import android.app.Activity;
import android.app.AlertDialog;

public class Utils {

	public static void showMessage(Activity activity, Message msg, String title, String message){
		AlertDialog.Builder caixa = new AlertDialog.Builder(activity);		
		caixa.setTitle(title);
		caixa.setMessage(message);

		if(msg == Message.ALERT)
			caixa.setIcon(R.drawable.alert);
		
		else if(msg == Message.ERROR)
			caixa.setIcon(R.drawable.error);
		
		caixa.setPositiveButton("OK", null);
		
		caixa.show();

	}
	
}
