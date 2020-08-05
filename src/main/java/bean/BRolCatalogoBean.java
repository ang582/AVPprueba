/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CRolCatalogoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.RolCatalogo;

/**
 *
 * @author angelcifuentes
 */
@ManagedBean
@SessionScoped
public class BRolCatalogoBean implements Serializable {

    private RolCatalogo rolCatalogoBean;
    private List<RolCatalogo> listaRolCatalogoBean;
    private CRolCatalogoDAO daoRolCatalogoBean;

    public BRolCatalogoBean() {
        this.rolCatalogoBean = new RolCatalogo();
        this.listaRolCatalogoBean = new ArrayList<>();
        this.daoRolCatalogoBean = new CRolCatalogoDAO();
    }

    //METODOS
    public void insertar(String nombre_rol) {

        //Si el id de la tabla ROL_CATALOGO ES IGUAL A 0, SE TOMA COMO NUEVO REGISTRO. DE LO CONTRARIO, SERÁ UNA MODIFICACIÓN
        if (rolCatalogoBean.getId_rol_catalogo() == 0) {
            this.daoRolCatalogoBean.agregar(nombre_rol);
        } else {
            this.daoRolCatalogoBean.modificar(rolCatalogoBean);
        }

    }

    public void eliminar(int id_rol_catalogo) {
        this.daoRolCatalogoBean.eliminar(id_rol_catalogo);
    }

    public void modificar(int id) {
        this.rolCatalogoBean = this.daoRolCatalogoBean.listar_por_id(id);
    }

    public void listar() {
        this.rolCatalogoBean = new RolCatalogo();

        this.listaRolCatalogoBean = this.daoRolCatalogoBean.listar_todos();
    }

    //GETTERS Y SETTERS
    public RolCatalogo getRolCatalogoBean() {
        return rolCatalogoBean;
    }

    public void setRolCatalogoBean(RolCatalogo rolCatalogoBean) {
        this.rolCatalogoBean = rolCatalogoBean;
    }

    public List<RolCatalogo> getListaRolCatalogoBean() {
        return listaRolCatalogoBean;
    }

    public void setListaRolCatalogoBean(List<RolCatalogo> listaRolCatalogoBean) {
        this.listaRolCatalogoBean = listaRolCatalogoBean;
    }

    public CRolCatalogoDAO getDaoRolCatalogoBean() {
        return daoRolCatalogoBean;
    }

    public void setDaoRolCatalogoBean(CRolCatalogoDAO daoRolCatalogoBean) {
        this.daoRolCatalogoBean = daoRolCatalogoBean;
    }

}
