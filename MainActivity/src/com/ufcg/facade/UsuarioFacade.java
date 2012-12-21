package com.ufcg.facade;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.ufcg.exception.DataBaseException;
import com.ufcg.sgbd.SGBD;

public class UsuarioFacade {
	private SGBD sgbd;

	public UsuarioFacade(SGBD banco){
		this.sgbd = banco;
	}
	public boolean cadastrarUsuario(String matricula)throws DataBaseException{

		ContentValues valores = new ContentValues();

		valores.put("matricula",matricula);

		return this.sgbd.insertValues("usuarios", valores) != -1;
	}
	
	public List<String> getAllMatriculas(){
		try {
			return sgbd.queryREFATORED(new String[] { "matricula" }, "usuarios");
		} catch (DataBaseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
