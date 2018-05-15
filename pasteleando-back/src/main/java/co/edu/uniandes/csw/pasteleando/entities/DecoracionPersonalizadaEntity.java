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
 *
 * @author dc.cepeda
 */
@Entity
public class DecoracionPersonalizadaEntity extends DecoracionEntity implements Serializable
{
     /**
     * Atributo que modela los pasteles de la DecoracionEntity
     */
    @PodamExclude
    @OneToMany(mappedBy = "decoracionPersonalizada")
    private List<PastelEntity> pasteles = new ArrayList<>(); 
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    /**
     * Atributo que modela el color de la DecoracionPersonalizada
     */
    private String color;
    /**
     * Atributo que modela el estado de la DecoracionPersonalizada
     */
    private String estado;
    
    private Integer peso;
      /**
     * Atributo que modela la foto de la DecoracionEntity
     */
    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    

    /**
     * @return peso
     */
    public Integer getPeso() {
        return peso;
    }
    /**
     * @return cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }
/**
     * @param cliente the atribute to set Cliente
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
     /**
     * @param peso the atribute to set
     */
    public void setPeso(Integer peso) {
        this.peso = peso;
    }
    
    
    
    /**
     * @return foto
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
     * @return the Estado
     */
    public String getEstado( )
    {
        return estado;
    }
    
    /**
     * @param estado the atribute to set
     */
    public void setEstado( String estado )
    {
        this.estado = estado;
    }

    /**
     * @return the pasteles
     */
    public List<PastelEntity> getPasteles() {
        return pasteles;
    }

    /**
     * @param pasteles the pasteles to set
     */
    public void setPasteles(List<PastelEntity> pasteles) {
        this.pasteles = pasteles;
    }

   

   
    
}
