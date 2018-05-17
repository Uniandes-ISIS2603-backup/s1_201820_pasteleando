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
 *Clase que representa el cliente y la base de datos y permite su serializacion
 * @author mp.bayonal
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{

    private Boolean tipoUsuario;
    private String formaPagoActual;
    private Integer numeroPuntos;
    private String name;
    private String correo;
    private String clave;

    @ElementCollection
    private List<String> tiposPagos;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<PqrsEntity> pqrs = new ArrayList<>( );
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<CalificacionEntity> calificaciones = new ArrayList<>( );
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<DecoracionPersonalizadaEntity> decoraciones = new ArrayList<>( );
    
    @PodamExclude
    @OneToOne(cascade = CascadeType.PERSIST)
    private CarritoEntity carrito;
         
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<FacturaEntity> facturas = new ArrayList<>();
    
    @PodamExclude
    @OneToOne(mappedBy = "cliente")
    private PedidoEntity pedido;

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }


    /**
     * retorna el tipo de usuario
     * @return tipoUsuario
     */
    public Boolean getTipoUsuario() {
        return this.tipoUsuario;
    }

    /**
     * actualiza el tipo de usuario
     * @param tipoUsuario the atribute to set
     */
    public void setTipoUsuario(Boolean tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * retorna la forma de pago actual
     * @return formaPagoActual
     */
    public String getFormaPagoActual() {
        return this.formaPagoActual;
    }

    /**
     * actualiza la forma de pago
     * @param formaPagoActual the atribute to set
     */
    public void setFormaPagoActual(String formaPagoActual) {
        this.formaPagoActual = formaPagoActual;
    }
    
    /**
     * retona el numero de puntos
     * @return numeroPuntos atribute to set
     */
    public Integer getNumeroPuntos()
    {
        return this.numeroPuntos;
    }
    
    /**
     * actualiza el numero de puntos
     * @param numeroPuntos atribute to set
     */
    public void setNumeroPuntos(Integer numeroPuntos) {
        this.numeroPuntos = numeroPuntos;
    }

    /**
     * retorna el nombre
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * actualiza el nombre
     * @param name the atribute to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * retorna el correo
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * actualiza el correo
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * retorna la clave
     * @return clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * actualiza la clave
     * @param clave
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /**
     * retorna tipos de pago
     * @return tiposPagos
     */
    public List<String> getTiposPagos() {
        return this.tiposPagos;
    }

    /**
     * actualiza tiposDePago
     * @param tiposPagos the atribute to set
     */
    public void setTiposPagos(List<String> tiposPagos) {
        this.tiposPagos = tiposPagos;
    }
    
    /**
     * retorna los PQRS
     * @return pqrs
     */
    public List<PqrsEntity> getPqrs() {
        return this.pqrs;
    }

    /**
     * actualiza la lista de PQRS
     * @param pqrs atribute to set
     */
    public void setPqrs(List<PqrsEntity> pqrs) {
        this.pqrs = pqrs;
    }

    /**
     * retorna las calificaciones
     * @return calificaciones
     */
    public List<CalificacionEntity> getCalificaciones() {
        return this.calificaciones;
    }

    /**
     * actualiza la lista de calificaciones
     * @param calificaciones atribute to ste
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
     /**
     * retornla la lista de decoraciones
     * @return decoraciones
     */
    public List<DecoracionPersonalizadaEntity> getDecoraciones() {
        return decoraciones;
    }

    /**
     * actualiza la lista de decoraciones
     * @param  atribute to set decoraciones 
     */
    public void setDecoraciones(List<DecoracionPersonalizadaEntity> decoraciones) {
        this.decoraciones = decoraciones;
    }

    /**
     * retorna el carrito
     * @return carrito
     */
    public CarritoEntity getCarrito() {
        return this.carrito;
    }

    /**
     * actualiza el carrito
     * @param carrito atribute to set
     */ 
    public void setCarrito(CarritoEntity carrito) {
        this.carrito = carrito;
    }
    
   /**
     * retorna la lista de facturas
     * @return 
     */
    public List<FacturaEntity> getFacturas() {
        return this.facturas;
    }
    
    /**
     * actualiza las facturas
     * @param facturas atribute to set
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

}
