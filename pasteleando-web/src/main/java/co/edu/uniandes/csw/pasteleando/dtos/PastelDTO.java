/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import java.io.Serializable;

/**
 * PastelDTO: obejeto de transferencia de datos para la entidad Pastel.
 *
 * represnetaciones de los JSON que se transfieren entre el cliente y el
 * servidor. Al serializarse como JSON esta clase implementa el siguiente
 * modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "peso": number,
 *      "precio": number
 *   }
 * </pre> Por ejemplo una entidad de Pastel se representa asi:<br>
 * <pre>
 *
 *   {
 *      "id":2891230
 *      "peso": 2,
 *      "precio": 280.9
 *   }
 *
 * </pre>
 *
 * @author MIGUELHOYOS
 */
public class PastelDTO implements Serializable {

    private Integer peso;
    private Double precio;

    /**
     * constructor vacio
     */
    public PastelDTO() {

    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param pastelEntity: Es la entidad que se va a convertir a DTO
     */
    public PastelDTO(PastelEntity pastelEntity) {
        if (pastelEntity != null) {
            this.peso = pastelEntity.getPeso();
            this.precio = pastelEntity.getPrecio();
        }
    }

    /**
     * retorna el peso del pastel
     *
     * @return peso Peso del pastel
     */
    public Integer getPeso() {
        return peso;
    }

    /**
     * actualiza el peso con el peso que llega por parametro
     *
     * @param pPeso Nuevo Peso del Pastel
     */

    public void setPeso(Integer pPeso) {
        this.peso = pPeso;
    }

    /**
     * retorna el precio del pastel
     *
     * @return precio Precio del Pastel
     */

    public double getPrecio() {
        return precio;
    }

    /**
     * actualiza el precio con el precio que llega por parametro
     *
     * @param pPrecio Nuevo precio del pastel
     */

    public void setPrecio(double pPrecio) {
        this.precio = pPrecio;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PastelEntity toEntity() {
        PastelEntity rta = new PastelEntity();
        rta.setPeso(this.peso);
        rta.setPrecio(this.precio);
        return rta;
    }
}
