/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Trabajador;

/**
 *
 * @author angelcifuentes
 */
public class CTrabajadorDAO extends Conexion implements ITrabajadorDAO{

    @Override
    public void agregar(Trabajador trabajador) {
        String sql="INSERT INTO trabajador (id_trabajador,"
                + "nombre1, nombre2, apellido1, apellido2, fecha_nac,"
                + "dpi, puesto) values (?,?,?,?,?,?,?,?);";
        
        try{
            this.conectar();
            
            cx.setAutoCommit(false);
            
            PreparedStatement stmt=cx.prepareStatement(sql);
            stmt.setInt(1, trabajador.getId_trabajador());
            stmt.setString(2, trabajador.getNombre1());
            stmt.setString(3, trabajador.getNombre2());
            stmt.setString(4, trabajador.getApellido1());
            stmt.setString(5, trabajador.getApellido2());
            stmt.setString(6, trabajador.getFecha_nac());
            stmt.setString(7, trabajador.getDpi());
            stmt.setString(8, trabajador.getPuesto());
            stmt.executeUpdate();
            cx.commit();
            stmt.close();
            
            System.out.println("LISTO ");
        }catch(SQLException e){
            e.printStackTrace();
            
            if(cx!=null){
                System.out.println("ROLLBACK");
                try{
                    cx.rollback();
                }catch(SQLException ex){
                    System.out.println("NO SE PUDO EJECUTAR ROLLBACK");
                    ex.printStackTrace();
                }
            }
        } finally{
            this.desconectar();
        }
    }

    @Override
    public void listar_por_id(int id_trabajador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}