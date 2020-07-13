/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

	private static Connection conn;
	private static final String driverCad = "com.mysql.jdbc.Driver";
	private static final String usuario = "root";
	private static final String contraseña = "";
	private static final String dbnombre = "db_cigarrillos";
	private static final String url = "jdbc:mysql://localhost:3306/"+dbnombre;
	
	//Constructor para establecer la conexion
	public Connector(){
		conn = null;
		try {
			Class.forName(driverCad);
			conn = DriverManager.getConnection(url, usuario, contraseña);
			if(conn != null) 
				System.out.println("Conexion a la "+dbnombre+ " realizada con exito");
		}catch(Exception e) {
			System.out.println("Error al conectar a la base de datos \n"+e);
		}
	}
	
	//Metodo para obtener la conexion establecida
	public Connection getConnection() {
		return conn;
	}
	
	public void desconectar() {
		try {
			conn.close();
			conn = null;
			System.out.println("Conexion terminada y finalizada");
		}catch(Exception e) {
			System.out.println("Hubo un error al finalizar la conexion\n"+e);
		}
	}
}
