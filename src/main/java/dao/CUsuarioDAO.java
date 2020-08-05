/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author angelcifuentes
 */
public class CUsuarioDAO extends Conexion implements IUsuarioDAO {

    @Override
    public void agregar(Usuario usuario, String passw) {
        String sql = "INSERT INTO usuario (id_trabajador, id_rol_catalogo,"
                + "usr, pass) values (?,?,?,?);";

        try {
            this.conectar();

            cx.setAutoCommit(false);

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setInt(1, usuario.getId_trabajador());
            stmt.setInt(2, usuario.getId_rol_catalogo());
            stmt.setString(3, usuario.getUsr());
            stmt.setString(4, passw);
            stmt.executeUpdate();
            cx.commit();
            stmt.close();

            System.out.println("LISTO");
        } catch (SQLException e) {
            e.printStackTrace();

            if (cx != null) {
                System.out.println("ROLLBACK");
                try {
                    cx.rollback();
                } catch (SQLException ex) {
                    System.out.println("NO SE PUDO EJECUTAR ROLLBACK");
                    ex.printStackTrace();
                }
            }
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuario listar_por_usr(String usr) {
        String sql = "select a.usr, a.pass, b.nombre_rol from usuario a\n"
                + "inner join rol_catalogo b\n"
                + "on a.id_rol_catalogo=b.id_rol_catalogo \n"
                + "where usr = ?;";

        String pass = null;

        Usuario usuario = new Usuario();

        try {
            this.conectar();

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setString(1, usr);

            ResultSet registro = stmt.executeQuery();

            while (registro.next()) {
                usuario.setUsr(registro.getString("usr"));
                usuario.setPass(registro.getString("pass"));
                usuario.setNombre_rol(registro.getString("nombre_rol"));
            }

            stmt.close();
            registro.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

        return usuario;
    }

    @Override
    public int listar_ultimo_usr(int id_trabajador) {
        String sql = "select a.id_usuario from usuario a\n"
                + "inner join trabajador b\n"
                + "on a.id_trabajador =b.id_trabajador \n"
                + "where a.id_trabajador =?;";

        int id_usr = 0;

        try {
            this.conectar();

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setInt(1, id_trabajador);

            ResultSet registro = stmt.executeQuery();

            while (registro.next()) {
                id_usr = registro.getInt("id_usuario");
            }

            stmt.close();
            registro.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

        return id_usr;
    }

}
