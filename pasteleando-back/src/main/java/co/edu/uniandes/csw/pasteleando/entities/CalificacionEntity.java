package co.edu.uniandes.csw.pasteleando.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *clase que representa una Calificacion en la persistencia y permite su serializacion
 * @author mp.bayonal
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    private Integer puntaje;
    
    private String comentario;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
    
    @PodamExclude
    @ManyToOne
    private DecoracionEntity decoracion;

    @PodamExclude
    @ManyToOne
    private PedidoEntity pedido;
 

    /**
     *retorna el puntaje
     * @return puntaje
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * actualiza el puntaje
     * @param puntaje the atribute to set
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * retorna el comentario
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * actualiza el comentario
     * @param comentario the atribute to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /** 
     * retorna el cliente
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * actualiza el cliente
     * @param cliente the atribute to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    /**
     * retorna la decoracion
     * @return decoracion
     */
    public DecoracionEntity getDecoracion() {
        return decoracion;
    }

    /**
     * actualiza la decoracion
     * @param decoracion the atribute to set
     */
    public void setDecoracion(DecoracionEntity decoracion) {
        this.decoracion = decoracion;
    }
    
    /**
     * retorna el pedido
     * @return pedido
     */
    public PedidoEntity getPedido() {
        return pedido;
    }

    /**
     * actualiza el pedido
     * @param pedido the atribute to set
     */
    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
