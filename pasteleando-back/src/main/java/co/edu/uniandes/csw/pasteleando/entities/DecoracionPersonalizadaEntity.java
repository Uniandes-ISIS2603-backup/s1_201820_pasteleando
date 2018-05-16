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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * clase que representa una decoracion personalizada en la base de datos y permite su serializacion
 * @author dc.cepeda
 */
@Entity
public class DecoracionPersonalizadaEntity extends DecoracionEntity implements Serializable
{
    
    private String color;
    private String estado;
    private Integer peso;
    
    @PodamExclude
    @OneToMany(mappedBy = "decoracionPersonalizada")
    private List<PastelEntity> pasteles = new ArrayList<>(); 
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    
    /**
     * retorna el color
     * @return color
     */
    public String getColor( )
    {
        return color;
    }
    
    /**
     * @param color the atribute to set
     */
    public void setColor( String color )
    {
        this.color = color;
    }
    /**
     * retorna el estado
     * @return the Estado
     */
    public String getEstado( )
    {
        return estado;
    }
    
    /**
     * establece el estado
     * @param estado the atribute to set
     */
    public void setEstado( String estado )
    {
        this.estado = estado;
    }
    
    /**
     * retorna el peso
     * @return peso
     */
    public Integer getPeso() {
        return peso;
    }
    
     /**
      * establece el peso
     * @param peso the atribute to set
     */
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    /**
     * retorna los psteles
     * @return the pasteles
     */
    public List<PastelEntity> getPasteles() {
        return pasteles;
    }

    /**
     * establece la lista de pasteles
     * @param pasteles the pasteles to set
     */
    public void setPasteles(List<PastelEntity> pasteles) {
        this.pasteles = pasteles;
    }
    
    /**
     * retorna el cliente
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }
    
    /**
     * establece el cliente
     * @param cliente the atribute to set Cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
 
}
