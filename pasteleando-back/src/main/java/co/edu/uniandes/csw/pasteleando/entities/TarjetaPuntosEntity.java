/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author m.leona
 */
@Entity
public class TarjetaPuntosEntity extends BaseEntity implements Serializable{
    
    @PodamIntValue(minValue = 0)
    private Integer numeroPuntos;
    
    @OneToOne
    @PodamExclude
    private ClienteEntity cliente;
    
    @OneToMany (mappedBy = "tarjetaPuntos")
    @PodamExclude
    private List<FacturaEntity> facturas = new ArrayList<>();

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }
    
    /**
    *@return Numero de puntos de la tarjeta
    */
    public Integer getNumeroPuntos()
    {
        return numeroPuntos;
    }
    /**
    *@param pPuntos nuevos puntos de la tarjeta
    */
    public void setNumeroPuntos(Integer pPuntos)
    {
        this.numeroPuntos=pPuntos;
    }
}
