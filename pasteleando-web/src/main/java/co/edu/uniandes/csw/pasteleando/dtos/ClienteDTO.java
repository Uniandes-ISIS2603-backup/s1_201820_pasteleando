/*
MIT License

Copyright (c) 2017 ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;


import javax.persistence.ElementCollection;
import java.util.*;

/**
 * PasteleandoDTO Objeto de transferencia de datos de la entidad de Pasteleando. Los DTO contienen las
 * represnetaciones de los JSON que se transfieren entre el cliente y el servidor.
 * <p>
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name: string,
 *      "atributo": string
 *   }
 * </pre>
 * Por ejemplo una entidad de Pasteleando se representa asi:<br>
 * <p>
 * <pre>
 *
 *   {
 *      "id": 91852,
 *      "name: "Bogota, DC",
 *      "atributo": "abcd123"
 *   }
 *
 * </pre>
 *
 * @author mp.bayonal
 */
public class ClienteDTO
{
	private Long id;
	private Integer idCarrito;
	private Boolean tipoUsuario;
	private String formaPagoActual;

	private List<String> tiposPagos;



	/**
	 * Constructor por defecto
	 */
	public ClienteDTO( )
	{
	}

	/**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param clienteEntity: Es la entidad que se va a convertir a DTO
	 */
	public ClienteDTO( ClienteEntity clienteEntity )
	{
		this.id = clienteEntity.getId();
		this.idCarrito = clienteEntity.getIdCarrito();
		this.tipoUsuario = clienteEntity.getTipoUsuario();
		this.formaPagoActual = clienteEntity.getFormaPagoActual();
		this.tiposPagos = clienteEntity.getTiposPagos();


	}



	/**
	 * @return El ID de la entidad Cliente
	 */
	public Long getId( )
	{
		return id;
	}

	/**
	 * @param id El nuevo ID
	 */
	public void setId( Long id )
	{
		this.id = id;
	}

	/**
	 * @return idCarrito
	 */
	public Integer getIdCarrito() {
		return idCarrito;
	}

	/**
	 * @param idCarrito the atribute to set
	 */
	public void setIdCarrito(Integer idCarrito) {
		this.idCarrito = idCarrito;
	}

	/**
	 * @return tipoUsuario
	 */
	public Boolean getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the atribute to set
	 */
	public void setTipoUsuario(Boolean tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return formaPagoActual
	 */
	public String getFormaPagoActual() {
		return formaPagoActual;
	}

	/**
	 * @param formaPagoActual the atribute to set
	 */
	public void setFormaPagoActual(String formaPagoActual) {
		this.formaPagoActual = formaPagoActual;
	}

	/**
	 * @return tiposPagos
	 */
	public List<String> getTiposPagos() {
		return tiposPagos;
	}

	/**
	 * @param tiposPagos the atribute to set
	 */
	public void setTiposPagos(List<String> tiposPagos) {
		this.tiposPagos = tiposPagos;
	}


	/**
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public ClienteEntity toEntity( )
	{
		ClienteEntity entity = new ClienteEntity( );
		entity.setId( this.id );
		entity.setIdCarrito(this.idCarrito);
		entity.setTipoUsuario(this.tipoUsuario);
		entity.setTiposPagos(this.tiposPagos);

		return entity;
	}
}
