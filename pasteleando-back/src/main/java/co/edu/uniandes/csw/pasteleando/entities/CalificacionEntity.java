package co.edu.uniandes.csw.pasteleando.entities;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 *
 * @author mp.bayonal
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable
{
    private Integer puntaje;

    private String comentario;

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
