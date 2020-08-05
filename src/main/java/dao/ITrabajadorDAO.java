/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Trabajador;

/**
 *
 * @author angelcifuentes
 */
public interface ITrabajadorDAO {
    
    public void agregar(Trabajador trabajador);
    public void listar_por_id(int id_trabajador);
    
}
