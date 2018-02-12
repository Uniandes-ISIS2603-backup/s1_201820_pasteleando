/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.dtos;

import co.edu.uniandes.csw.pasteleando.entities.PqrsEntity;

/**
 *
 * @author ni.ramirez10
 */
public class PqrsDTO 
{
     private int tipo; 
    
    private int idSolicitud; 
    
    private int idCliente; 
    
    private String fecha; 
    
    private String estado; 
    
    /**
	 * Constructor por defecto
	 */
	public PqrsDTO( )
	{
	}
 /**
	 * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
	 * la entidad que viene de argumento.
	 *
	 * @param pqrsDTO: Es la entidad que se va a convertir a DTO
	 */
	public PqrsDTO( PqrsDTO pqrsDTO )
	{
		this.tipo = pqrsDTO.getTipo(); 
                this.idSolicitud = pqrsDTO.getIdSolicitud(); 
                this.idCliente = pqrsDTO.getIdCliente(); 
                this.fecha = pqrsDTO.getFecha(); 
		this.estado = pqrsDTO.getEstado(); 
	}
    
    
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
	 * Convertir DTO a Entity
	 *
	 * @return Un Entity con los valores del DTO
	 */
	public PqrsEntity toEntity( )
	{
		PqrsEntity entity = new PqrsEntity( );
		entity.setTipo(this.tipo);
                entity.setIdSolicitud(this.idSolicitud);
                entity.setIdCliente(this.idCliente);
                entity.setFecha(this.fecha);
                entity.setEstado(this.estado);
		return entity;
	}
    
    
}
