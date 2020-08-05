/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.RolCatalogo;

/**
 *
 * @author angelcifuentes
 */
public interface IRolCatalogoDAO {
    
    public void agregar(String nombre_rol);
    public List<RolCatalogo> listar_todos();
    public RolCatalogo listar_por_id(int id);
    public void modificar(RolCatalogo rolCatalogo);
    public void eliminar(int id_rolCatalogo);
    
}
