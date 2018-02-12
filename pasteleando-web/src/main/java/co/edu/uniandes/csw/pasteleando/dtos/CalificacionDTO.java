package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;


/**
 *
 * @author mp.bayonal
 */
public class CalificacionDTO
{


    private Long id;

    private Integer puntaje;

    private String comentario;


    public CalificacionDTO( )
    {
    }

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

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param calificacionEntity: Es la entidad que se va a convertir a DTO
     */
    public CalificacionDTO( CalificacionEntity calificacionEntity )
    {

        this.puntaje = calificacionEntity.getPuntaje();
        this.comentario = calificacionEntity.getComentario();

    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */

    public CalificacionEntity toEntity( )
    {
        CalificacionEntity entity = new CalificacionEntity( );
        entity.setComentario(this.comentario);
        entity.setPuntaje(this.puntaje);
        return entity;
    }

}