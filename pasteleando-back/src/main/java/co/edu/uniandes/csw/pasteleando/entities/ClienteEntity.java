package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

/**
 *
 * @author mp.bayonal
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{

    private Long id;
    private Integer idCarrito;
    private Boolean tipoUsuario;
    private String formaPagoActual;

    @ElementCollection
    private List<String> tiposPagos;

    @OneToMany
    private List<CalificacionEntity> calificaciones;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the atribute to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return idCarrito
     */
    public Integer getIdCarrito() {
        return idCarrito;
    }

    /**
     * @param idCarrito the atribute to set
     */
    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    /**
     * @return tipoUsuari
     */
    public Boolean getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the atribute to set
     */
    public void setTipoUsuario(Boolean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return formaPagoActual
     */
    public String getFormaPagoActual() {
        return formaPagoActual;
    }

    /**
     * @param formaPagoActual the atribute to set
     */
    public void setFormaPagoActual(String formaPagoActual) {
        this.formaPagoActual = formaPagoActual;
    }

    /**
     * @return tiposPagos
     */
    public List<String> getTiposPagos() {
        return tiposPagos;
    }

    /**
     * @param tiposPagos the atribute to set
     */
    public void setTiposPagos(List<String> tiposPagos) {
        this.tiposPagos = tiposPagos;
    }

    /**
     * @return Calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the atribute to set
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
}