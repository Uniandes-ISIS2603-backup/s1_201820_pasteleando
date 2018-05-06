/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.PedidoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ni.ramirez10
 */

@RunWith(Arquillian.class)
public class PedidoPersistenceTest 
{
    /**
     * Inyección de la dependencia a la clase PedidoPersistence cuyos métodos
     * se van a probar.
     */
    
    @Inject
    private PedidoPersistence pedidoPersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Contexto transacional
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Atributo que modela una lista de pedidos.
     */
    private List<PedidoEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Pedido, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PedidoEntity.class.getPackage())
                .addPackage(PedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
                e1.printStackTrace();
            }
        }
    }
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    
    private void clearData() 
    {
        em.createQuery("delete from PedidoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
     
     private void insertData() 
     {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) 
        {
            PedidoEntity entity = factory.manufacturePojo(PedidoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear un Pedido
     */
    
    @Test
    public void createPedidoTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        PedidoEntity result = pedidoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PedidoEntity entity = em.find(PedidoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar un pedido.
     */
    
    @Test
    public void getPedidoTest() 
    {
        PedidoEntity entity = data.get(0);
        PedidoEntity newEntity = pedidoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar un pedido.
     */
    
    @Test
    public void updatePedidoTest() 
    {
        PedidoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PedidoEntity newEntity = factory.manufacturePojo(PedidoEntity.class);
        
        newEntity.setId(entity.getId());
        
        pedidoPersistence.update(newEntity);
        
        PedidoEntity resp = em.find(PedidoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar un pedido.
     */
    
    @Test
    public void deletePedidoTest() 
    {
        PedidoEntity entity = data.get(0);
        pedidoPersistence.delete(entity.getId());
        PedidoEntity deleted = em.find(PedidoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para consultar la lista de pedidos.
     */
    @Test
    public void getPedidosTest() 
    {
        PedidoEntity entity = data.get(0);
        PedidoEntity newEntity = pedidoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado()); 
    }
    
    /**
     *prueba para encontrar un pedido por el nombre 
     */
    @Test
    public void findByNameTest()
    {
        PedidoEntity ent = data.get(0);
        PedidoEntity entEnc = pedidoPersistence.findByName(ent.getName());
        Assert.assertEquals(entEnc, ent);
    }


    
}
