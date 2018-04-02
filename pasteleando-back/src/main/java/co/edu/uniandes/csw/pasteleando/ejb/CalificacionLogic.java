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
package co.edu.uniandes.csw.pasteleando.ejb;

import co.edu.uniandes.csw.pasteleando.entities.CalificacionEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.CalificacionPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author ISIS2603
 */
@Stateless
public class CalificacionLogic
{

	private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName( ) );

	@Inject
	private CalificacionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

	public CalificacionEntity create( CalificacionEntity entity ) throws BusinessLogicException
	{
		LOGGER.info( "Inicia proceso de creación de una entidad de Calificacion" );
		// Verifica la regla de negocio que dice que no puede haber dos entidades de Pasteleandos con el mismo nombre
		if( persistence.findByName( entity.getComentario() ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de Calificacion con el nombre \"" + entity.getName( ) + "\"" );
		}
		// Invoca la persistencia para crear la entidad de Pasteleando
		persistence.create( entity );
		LOGGER.info( "Termina proceso de creación de entidad de Calificacion" );
		return entity;
	}

	public List<CalificacionEntity> getAll( )
	{
		LOGGER.info( "Inicia proceso de consultar todas las entidades de Calificacion" );
		// Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
		List<CalificacionEntity> entities = persistence.findAll( );
		LOGGER.info( "Termina proceso de consultar todas las entidades de Calificacion" );
		return entities;
	}

	public CalificacionEntity getById( Long id )
	{
		return persistence.find( id );
	}

	public CalificacionEntity update( CalificacionEntity entity ) throws BusinessLogicException
	{
		if( persistence.findByName( entity.getName( ) ) != null )
		{
			throw new BusinessLogicException( "Ya existe una entidad de Calificacion con el nombre \"" + entity.getName( ) + "\"" );
		}
		return persistence.update( entity );
	}

	public void delete( CalificacionEntity entity ) throws BusinessLogicException
	{
           //TODO: este método debe recibir un id y hay que validar que existe un Calificacion con ese id 
		LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Calificacion con id={0}", entity.getId( ) );
		
                
                if( persistence.find(entity.getId()) == null )
		{
			throw new BusinessLogicException( "NO existe una entidad de Calificacion con el id \"" + entity.getId() + "\"" );
		}
                persistence.delete( entity.getId() );
		LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Calificacion con id={0}", entity.getId( ) );
	}
}
