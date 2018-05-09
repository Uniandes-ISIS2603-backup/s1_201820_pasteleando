/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link DecoracionCatalogoDTO} para manejar las
 * relaciones entre las DecoracionCatalogoDTO JSON y otros DTOs. Para conocer el
 * contenido de la clase vaya a la documentacion de
 * {@link DecoracionCatalogoDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id":number,
 *      "categoria": String,
 *      "promociones":
 *      [
 *      {
 *      "cantidad": int
 *      },
 *      {
 *      "cantidad": int
 *      }
 *      ]
 *   }
 * </pre> Por ejemplo una decoración catálogo se representa asi:<br>
 *
 * <pre>
 *   {
 *      "id":number,
 *      "categoria": "Bodas",
 *      "promociones":
 *      [
 *      {
 *      "cantidad": 30
 *      },
 *      {
 *      "cantidad": 10
 *      }
 *      ]
 *   }
 * </pre>
 *
 * @author jf.garcia
 */
public class DecoracionCatalogoDetailDTO extends DecoracionCatalogoDTO {

    private List<PastelDTO> pasteles;
    /**
     * Relación a una o varias promociones.
     */
    private List<PromocionDTO> promociones;

    /**
     * Constructor por defecto.
     */
    public DecoracionCatalogoDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de Pasteleando a partir de la cual se construye
     * el objeto
     */
    public DecoracionCatalogoDetailDTO(DecoracionCatalogoEntity entity) {
        super(entity);
        // TODO: pasteles
        if (entity.getPasteles() != null) {
            pasteles = new ArrayList<>();
            entity.getPasteles().forEach((PastelEntity entityPastel) -> {
                pasteles.add(new PastelDTO(entityPastel));
            });
        }
        if (entity.getPromociones() != null) {
            promociones = new ArrayList<>();
            entity.getPromociones().forEach((PromocionEntity entityPromocion) -> {
                promociones.add(new PromocionDTO(entityPromocion));
            });
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    
    public DecoracionCatalogoEntity toEntity() {
        DecoracionCatalogoEntity entity = new DecoracionCatalogoEntity();
        super.toEntity(entity);
        if (getPasteles() != null) {
            List<PastelEntity> pastelesEntity = new ArrayList<>();
            getPasteles().forEach((dtoPastel) -> {
                pastelesEntity.add(dtoPastel.toEntity());
            });
            entity.setPasteles(pastelesEntity);
        }

        if (getPromociones() != null) {
            List<PromocionEntity> promocionEntity = new ArrayList<>();
            getPromociones().forEach((dtoPromocion) -> {
                promocionEntity.add(dtoPromocion.toEntity());
            });
            entity.setPromociones(promocionEntity);
        }
        return entity;
    }

    /**
     * Devuelve las promociones asociadas a esta decoración del catálogo.
     *
     * @return Lista de DTOs de Promocion
     */
    public List<PromocionDTO> getPromociones() {
        return promociones;
    }

    /**
     * Modifica las promociones asociadas a esta decoración del catálogo.
     *
     * @param promociones Las nuevas promociones.
     */
    public void setPromociones(List<PromocionDTO> promociones) {
        this.promociones = promociones;
    }

    /**
     * @return the pasteles
     */
    public List<PastelDTO> getPasteles() {
        return pasteles;
    }

    /**
     * @param pasteles the pasteles to set
     */
    public void setPasteles(List<PastelDTO> pasteles) {
        this.pasteles = pasteles;
    }

}
