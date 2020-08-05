/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CTrabajadorDAO;
import dao.CUsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Trabajador;
import modelo.Usuario;

/**
 *
 * @author angelcifuentes
 */
@ManagedBean
@SessionScoped
public class BTrabajadorBean implements Serializable {
    
    private Trabajador trabajadorBean;
    private List<Trabajador> listaTrabajadorBean;
    private CTrabajadorDAO daoTrabajadorBean;
    
    private Usuario usuarioBean;
    private List<Usuario> listaUsuarioBean;
    private CUsuarioDAO daoUsuarioBean;
    
    private BLoginBean loginBean;
    
    public BTrabajadorBean() {
        this.trabajadorBean = new Trabajador();
        this.listaTrabajadorBean = new ArrayList<>();
        this.daoTrabajadorBean = new CTrabajadorDAO();
        
        this.usuarioBean = new Usuario();
        this.listaUsuarioBean = new ArrayList<>();
        this.daoUsuarioBean = new CUsuarioDAO();
        
        this.loginBean = new BLoginBean();
    }

    //METODOS
    public void insertar() {
        //Insertando datos en tabla trabajador
        this.daoTrabajadorBean.agregar(this.trabajadorBean);
        
        //Asignando id_trabajador del modelo trabajador al modelo usuario
        this.usuarioBean.setId_trabajador(this.trabajadorBean.getId_trabajador());
        
        //Se toma la contraseña que se ingresó en el formulario, se convierte en SHA1
        String passEncr = this.loginBean.encripta(this.usuarioBean.getPass());
        
        //Insertando datos en tabla usuario
        this.daoUsuarioBean.agregar(this.usuarioBean, passEncr);
        
        //Reseteando modelos
        this.trabajadorBean=new Trabajador();
        this.usuarioBean=new Usuario();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención", "Trabajador registrado correctamente."));
        
    }
    
    

    
    //GETTERS Y SETTERS
    public Trabajador getTrabajadorBean() {
        return trabajadorBean;
    }

    public void setTrabajadorBean(Trabajador trabajadorBean) {
        this.trabajadorBean = trabajadorBean;
    }

    public List<Trabajador> getListaTrabajadorBean() {
        return listaTrabajadorBean;
    }

    public void setListaTrabajadorBean(List<Trabajador> listaTrabajadorBean) {
        this.listaTrabajadorBean = listaTrabajadorBean;
    }

    public CTrabajadorDAO getDaoTrabajadorBean() {
        return daoTrabajadorBean;
    }

    public void setDaoTrabajadorBean(CTrabajadorDAO daoTrabajadorBean) {
        this.daoTrabajadorBean = daoTrabajadorBean;
    }

    public Usuario getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(Usuario usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<Usuario> getListaUsuarioBean() {
        return listaUsuarioBean;
    }

    public void setListaUsuarioBean(List<Usuario> listaUsuarioBean) {
        this.listaUsuarioBean = listaUsuarioBean;
    }

    public CUsuarioDAO getDaoUsuarioBean() {
        return daoUsuarioBean;
    }

    public void setDaoUsuarioBean(CUsuarioDAO daoUsuarioBean) {
        this.daoUsuarioBean = daoUsuarioBean;
    }
    
}
