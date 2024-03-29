package com.ufcg.sgbd;

public class ScriptSQL {

	public final static String disciplinasKEYS [] = {"KEY_disciplina","nome","requesito"};
	public final static String disciplinas = "CREATE TABLE IF NOT EXISTS disciplinas("+
			"KEY_disciplina INTEGER PRIMARY KEY,"+
			"nome VARCHAR(7),"+	
			"semestre INTEGER,"+
			"cursando BOOLEAN,"+
			"cursada BOOLEAN" +
			"creditos INTEGER" +
			");";
	
	public final static String usuariosKEYS [] = {"KEY_matricula","matricula"};
	public final static String usuarios = "CREATE TABLE IF NOT EXISTS usuarios("+
			"id INT AUTO_INCREMENT,"+
			"matricula VARCHAR(10),"+	
			"primary key(id));";
	
	public final static String requisitos = "CREATE TABLE IF NOT EXISTS requisitos("+
			"id INT AUTO_INCREMENT,"+
			"disciplina VARCHAR(50),"+	
			"requisito VARCHAR(30)"+ 
			");";
	
}