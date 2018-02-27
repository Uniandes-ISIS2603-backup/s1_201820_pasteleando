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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ni.ramirez10
 */
@Entity
public class PqrsEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    
    private int tipo; 
    
    private int idSolicitud; 
    
    private int idCliente; 
    
    private String fecha; 
    
    private String estado; 
    
    private List<PqrsEntity> pqrs = new ArrayList<PqrsEntity>();
    
     /**
	 * @return El tipo de la solicitud
	 */
	public int getTipo( )
	{
		return tipo;
	}
        
        /**
	 * @param pTipo El nuevo tipo de solicitud
	 */
	public void setTipo( int pTipo )
	{
		this.tipo = pTipo;
	}
        
         /**
	 * @return El id de la solicitud
	 */
	public int getIdSolicitud( )
	{
		return idSolicitud;
	}
        
        /**
	 * @param pIdSolicitud El nuevo id de la solicitud
	 */
	public void setIdSolicitud( int pIdSolicitud )
	{
		this.idSolicitud = pIdSolicitud;
	}
        
         /**
	 * @return El id del cliente
	 */
	public int getIdCliente( )
	{
		return idCliente;
	}
        
        /**
	 * @param pIdCliente El nuevo id del cliente
	 */
	public void setIdCliente( int pIdCliente )
	{
		this.idCliente = pIdCliente;
	}
        
         /**
	 * @return La fecha de la solicitud
	 */
	public String getFecha() 
	{
		return fecha;
	}
        
        /**
	 * @param pFecha La nueva fecha se la solicitud
	 */
	public void setFecha( String pFecha )
	{
		this.fecha = pFecha ;
	}
        
        /**
	 * @return El estado de la solicitud
	 */
	public String getEstado() 
	{
		return estado;
	}
        
        /**
	 * @param pEstado El nuevo estado de la solicitud
	 */
	public void setEstado( String pEstado )
	{
		this.estado = pEstado ;
	}
        
        /**
         * Obtiene la lista de pqrs.
         *
         * @return lista de pqrs.
         */
         public List<PqrsEntity> getPqrs() 
         {
             return pqrs;
         }

        /**
         * Establece el valor de la lista de pqrs.
         *
         * @param pPqrs nuevo valor de la lista.
         */
         public void setPqrs(List<PqrsEntity> pPqrs) 
         {
            this.pqrs = pPqrs;
         }
    
}
