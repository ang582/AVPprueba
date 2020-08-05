/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Usuario;

/**
 *
 * @author angelcifuentes
 */
public interface IUsuarioDAO {
    
    public void agregar(Usuario usuario, String passw);
    
    public Usuario listar_por_usr(String usr);
    
    public int listar_ultimo_usr(int id_usr);
    
}
