package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mp.bayonal
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{

    private Boolean tipoUsuario;
    private String formaPagoActual;

    @ElementCollection
    private List<String> tiposPagos;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<PqrsEntity> pqrs = new ArrayList<>( );
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<CalificacionEntity> calificaciones = new ArrayList<>( );
    
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private CarritoEntity carrito;
    
    @PodamExclude
    @OneToOne
    private TarjetaPuntosEntity tarjeta;

    public List<PqrsEntity> getPqrs() {
        return pqrs;
    }

    public void setPqrs(List<PqrsEntity> pqrs) {
        this.pqrs = pqrs;
    }

    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public CarritoEntity getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }

    public TarjetaPuntosEntity getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaPuntosEntity tarjeta) {
        this.tarjeta = tarjeta;
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

    
}
