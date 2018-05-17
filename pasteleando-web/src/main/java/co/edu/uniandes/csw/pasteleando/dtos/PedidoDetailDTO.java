/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link PedidoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos. Para conocer el
 * contenido del pedido vaya a la documentacion de {@link PedidoDTO}
 *
 * @author ni.ramirez10
 */
public class PedidoDetailDTO extends PedidoDTO {

    private List<PedidoDTO> pedidos;

    /**
     * Constructor por defecto
     */
    public PedidoDetailDTO() {
        super();
    }

    /**
     * Obtiene la lista de pedidos
     *
     * @return Los pedidos
     */
    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    /**
     * Modifica la lista de pedidos
     *
     * @param pPedidos Los pedidos a establecer
     */
    public void setPedidos(List<PedidoDTO> pPedidos) {
        this.pedidos = pPedidos;
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity La entidad de Pasteleando a partir de la cual se construye
     * el objeto
     */
    public PedidoDetailDTO(PedidoEntity entity) {
        super(entity);
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return La entidad construida a partir del DTO.
     */
    @Override
    public PedidoEntity toEntity() {
        PedidoEntity entity = super.toEntity();
        return entity;
    }

}
