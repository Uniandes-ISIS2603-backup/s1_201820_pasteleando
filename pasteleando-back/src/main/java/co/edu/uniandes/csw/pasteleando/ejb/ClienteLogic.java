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

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;

import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.ClientePersistence;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * clase que implementa la conexion de la entidad Cliente
 * @author mpbayonal
 */
@Stateless
public class ClienteLogic
{

	private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName( ) );

        // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
	@Inject
	private ClientePersistence persistence; 
        
        @Inject
        private PedidoLogic pedidoLogic;
    

        /**
         * crea una entidad Cliente en la base de datos
         * @param entity entidad a crearse
         * @return la entidad creada
         * @throws BusinessLogicException si ya existe una entidad en la base de datos con el ID de la entidad dadda por parametro
         */
        public ClienteEntity create( ClienteEntity entity ) throws BusinessLogicException
	{
            LOGGER.info( "Inicia proceso de creación de una entidad de Pasteleando" );
            // Verifica la regla de negocio que dice que no puede haber dos entidades de Pasteleandos con el mismo nombre
            if( persistence.findByName(entity.getName())  != null )
		{
		  throw new BusinessLogicException( "Ya existe una entidad de Pasteleando con el id \"" + entity.getId() + "\"" );
		}
               
	    // Invoca la persistencia para crear la entidad de Pasteleando
	    persistence.create( entity );
                
	    LOGGER.info( "Termina proceso de creación de entidad de Cliente" );
	    return entity;
	}

	/**
         * retorna todas las entidades Cliente en la base de datos
         * @return 
         */
        public List<ClienteEntity> getAll( )
	{
	    LOGGER.info( "Inicia proceso de consultar todas las entidades de Cliente" );
	    // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
	    List<ClienteEntity> entities = persistence.findAll( );
	    LOGGER.info( "Termina proceso de consultar todas las entidades de Cliente" );
            return entities;
	}

	/**
         * retorna toda la informacion de la entodad Cliente con el ID dado por parametro
         * @param id ID de la entidad Cliente
         * @return entidad Cliente
         * @throws BusinessLogicException si no existe un Cliente con el ID dado
         */
        public ClienteEntity getById( Long id ) throws BusinessLogicException
	{
            ClienteEntity ent =  persistence.find( id );
            if(ent == null)
            {
                throw new BusinessLogicException( "NO existe una entidad de Cliente con el id ingresado" );
            }
            return ent;
	}

        /**
         * actualiza una entidad Cliente en la base de datos
         * @param entity la entidad a actualizar
         * @return la entidad actualizada
         * @throws BusinessLogicException si no existe la entidad que llega por parametro en la base de datos
         */
        public ClienteEntity update( ClienteEntity entity ) throws BusinessLogicException
	{
            if( persistence.find(entity.getId())  == null )
            {
                throw new BusinessLogicException( "NO existe una entidad de Cliente con el id \"" + entity.getId() + "\"" );
            }
                
            return persistence.update( entity );
	}

	/**
         * elimina uan entidad Cliente de la base de datos
         * @param entity la entidad a eliminar
         * @throws BusinessLogicException si no existe la entidad que llega por parametro en la base de datos
         */ 
        public void delete( ClienteEntity entity ) throws BusinessLogicException
	{
            LOGGER.log( Level.INFO, "Inicia proceso de borrar la entidad de Cliente con id={0}", entity.getId( ) );
            //TODO: este método debe recibir un id y hay que validar que existe un Cliente con ese id
            if( persistence.find(entity.getId())  == null )
            {
		throw new BusinessLogicException( "NO existe una entidad de Cliente con el id \"" + entity.getId() + "\"" );
            }
            //carritoPersistence.delete(entity.getCarrito().getId());
            persistence.delete( entity.getId() );
            LOGGER.log( Level.INFO, "Termina proceso de borrar la entidad de Cliente con id={0}", entity.getId( ) );
	}
}
