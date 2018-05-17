/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.util.ArrayList;
import java.util.List;

/**
 ** *Un ejemplo de este DTO es:
 * <br>
 * <pre>
 *{
 *"color":"Azul",
 *  "imagen": "C:\Users\dc.cepeda\Desktop\cake.jpg",
 *  "estado": "aprobado",
 *"Decoracion":[{
 * "esPersonalizada: true
 * }]
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaDetailDTO extends DecoracionPersonalizadaDTO {

    private List<PastelDTO> pasteles;
    private transient ClienteDTO cliente;

    /**
     * Constructor por defecto
     */
    public DecoracionPersonalizadaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de Pasteleando a partir de la cual se construye
     * el objeto
     */
    public DecoracionPersonalizadaDetailDTO(DecoracionPersonalizadaEntity entity) {
        super(entity);
        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        }
        if (entity.getPasteles() != null) {
            pasteles = new ArrayList<>();
            entity.getPasteles().forEach((PastelEntity entityPastel) -> {
                pasteles.add(new PastelDTO(entityPastel));
            });
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    public DecoracionPersonalizadaEntity toEntity() {
        DecoracionPersonalizadaEntity entity = new DecoracionPersonalizadaEntity();
        super.toEntity(entity);
        if (getPasteles() != null) {
            List<PastelEntity> pastelesEntity = new ArrayList<>();
            getPasteles().forEach((dtoPastel) -> {
                pastelesEntity.add(dtoPastel.toEntity());
            });
            entity.setPasteles(pastelesEntity);
        }
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        return entity;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

}
