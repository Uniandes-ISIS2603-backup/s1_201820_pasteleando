/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersonalizadaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author dc.cepeda
 */
public class DecoracionPersonalizadaLogic 
{
    private static final Logger LOGGER = Logger.getLogger( DecoracionPersonalizadaLogic.class.getName( ) );

	@Inject
	private DecoracionPersonalizadaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

	public DecoracionPersonalizadaEntity create( DecoracionPersonalizadaEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de DecoracionPersonalizada" );
		// Verifica la regla de negocio que dice que no puede haber dos entidades de DecoracionPersonalizadaes con el mismo nombre
		if( persistence.findByName( entity.getName( ) ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de DecoracionPersonalizada con el nombre \"" + entity.getName( ) + "\"" );
		}
		// Invoca la persistencia para crear la entidad de DecoracionPersonalizada
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de DecoracionPersonalizada" );
		return entity;
	}

	public List<DecoracionPersonalizadaEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de DecoracionPersonalizada" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<DecoracionPersonalizadaEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de DecoracionPersonalizada" );
		return entities;
	}

	public DecoracionPersonalizadaEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public DecoracionPersonalizadaEntity update( DecoracionPersonalizadaEntity entity ) throws BusinessLogicException
	{
		if( persistence.findByName( entity.getName( ) ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de DecoracionPersonalizada con el nombre \"" + entity.getName( ) + "\"" );
		}
		return persistence.update( entity );
	}

	public void delete( DecoracionPersonalizadaEntity entity ) throws BusinessLogicException
	{
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de DecoracionPersonalizada con id={0}", entity.getId( ) );
		persistence.delete( entity );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de DecoracionPersonalizada con id={0}", entity.getId( ) );
	}
}
