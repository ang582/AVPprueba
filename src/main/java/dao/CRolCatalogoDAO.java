/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.RolCatalogo;

/**
 *
 * @author angelcifuentes
 */
public class CRolCatalogoDAO extends Conexion implements IRolCatalogoDAO {

    @Override
    public void agregar(String nombre_rol) {
        String sql = "insert into rol_catalogo (nombre_rol) values (?);";

        try {
            this.conectar();

            cx.setAutoCommit(false);

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setString(1, nombre_rol);
            stmt.executeUpdate();
            cx.commit();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (cx != null) {
                try {
                    cx.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("NO SE PUDO EJECUTAR ROLLBACK");
                }
            }
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<RolCatalogo> listar_todos() {
        String sql = "select * from rol_catalogo;";

        List<RolCatalogo> lista = new ArrayList<>();

        try {
            this.conectar();

            PreparedStatement stmt = cx.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            //LLENANDO LA LISTA CON LO OBTENIDO DEL RESULTSET
            while (registros.next()) {
                RolCatalogo rolCatalogo = new RolCatalogo();

                rolCatalogo.setId_rol_catalogo(registros.getInt("id_rol_catalogo"));
                rolCatalogo.setNombre_rol(registros.getString("nombre_rol"));

                lista.add(rolCatalogo);
            }

            stmt.close();
            registros.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public void modificar(RolCatalogo rolCatalogo) {
        String sql = "UPDATE rol_catalogo SET nombre_rol = ? WHERE id_rol_catalogo = ?;";

        try {
            this.conectar();

            cx.setAutoCommit(false);

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setString(1, rolCatalogo.getNombre_rol());
            stmt.setInt(2, rolCatalogo.getId_rol_catalogo());
            stmt.executeUpdate();
            cx.commit();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();

            if (cx != null) {
                try {
                    cx.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void eliminar(int id_rolCatalogo) {
        String sql = "DELETE FROM rol_catalogo where id_rol_catalogo=?;";

        try {
            this.conectar();

            cx.setAutoCommit(false);

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setInt(1, id_rolCatalogo);
            stmt.executeUpdate();
            cx.commit();
            stmt.close();
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
    public RolCatalogo listar_por_id(int id) {
        String sql = "select * from rol_catalogo where id_rol_catalogo=?;";

        RolCatalogo rolCatalogo = new RolCatalogo();

        try {
            this.conectar();

            PreparedStatement stmt = cx.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet registro = stmt.executeQuery();

            //LLENANDO LA LISTA CON LO OBTENIDO DEL RESULTSET
            while (registro.next()) {

                rolCatalogo.setId_rol_catalogo(registro.getInt("id_rol_catalogo"));
                rolCatalogo.setNombre_rol(registro.getString("nombre_rol"));

            }

            stmt.close();
            registro.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        
        return rolCatalogo;
    }

}
