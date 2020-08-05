/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author angelcifuentes
 */
public class Conexion {
    
    protected Connection cx;

    public void conectar() {

        String url = "jdbc:postgresql://localhost:5432/testrolbd";
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String password = "intecap";

        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void desconectar() {
        if (cx != null) {
            try {
                if (!cx.isClosed()) {
                    cx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
