/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.CUsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.RolCatalogo;
import modelo.Usuario;

/**
 *
 * @author angelcifuentes
 */
@ManagedBean
@SessionScoped
public class BLoginBean implements Serializable {

    private Usuario usuarioBean;
    private List<Usuario> listaUsuarioBean;
    private CUsuarioDAO daoUsuarioBean;
    
    private RolCatalogo rolCatalogoBean;

    public BLoginBean() {
        this.usuarioBean = new Usuario();
        this.listaUsuarioBean = new ArrayList<>();
        this.daoUsuarioBean = new CUsuarioDAO();
        
        this.rolCatalogoBean = new RolCatalogo();
    }

    public void login(String usr, String pass) {
        
        //Obteniendo password desde bd según usuario insertado
        this.usuarioBean=this.daoUsuarioBean.listar_por_usr(usr);
        
        String passObtenida = this.usuarioBean.getPass();

        //Convertir cadena recibida a SHA1 para compararla con la que está en BD
        String passConvertida = this.encripta(pass);

        if (passConvertida.equals(passObtenida)) {
            System.out.println("CONTRASEÑA CORRECTA");

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
            } catch (IOException ex) {
                System.out.println("ERROR EN REDIRECCIONAMIENTO AL INTENTAR INGRESAR A PAGINA SIN PERMISO");
                ex.printStackTrace();
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atención", "Contraseña incorrecta o usuario no registrado."));
            System.out.println("CONTRASEÑA INCORRECTA");
        }
        

    }

    public String dirige() {
        String pagina = null;

        return pagina;
    }

    //METODOS PARA ENCRIPTAR CONTRASEÑA CON SHA1
    public String encripta(String pass) {
        String sha1 = "";

        try {
            MessageDigest encript = MessageDigest.getInstance("SHA-1");
            encript.reset();
            encript.update(pass.getBytes("UTF-8"));

            //Llama al metodo convHexa que convierte la cadena a hexadecimal
            sha1 = convHexa(encript.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sha1;
    }

    public String convHexa(final byte[] hash) {
        Formatter formateador = new Formatter();

        for (byte b : hash) {
            formateador.format("%02x", b);
        }

        String resultado = formateador.toString();
        formateador.close();

        return resultado;
    }
    
    public void resetear(){
        this.usuarioBean= new Usuario();
    }
    
    //GETTERS Y SETTERS

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

    public RolCatalogo getRolCatalogoBean() {
        return rolCatalogoBean;
    }

    public void setRolCatalogoBean(RolCatalogo rolCatalogoBean) {
        this.rolCatalogoBean = rolCatalogoBean;
    }
    
}
