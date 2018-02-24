package co.edu.uniandes.csw.pasteleando.entities;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.ManyToOne;

/**
 *
 * @author mp.bayonal
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    private Integer puntaje;

    private String comentario;
    
    @ManyToOne
    private ClienteEntity cliente;
    
    @ManyToOne
    private DecoracionEntity decoracion;

    public DecoracionEntity getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(DecoracionEntity decoracion) {
        this.decoracion = decoracion;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    

    /**
     * @return puntaje
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the atribute to set
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the atribute to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
