/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.runner.RunWith;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



/**
 *
 * @author m.leona
 */
@RunWith(Arquillian.class)

public class TarjetaPuntosPersistenceTest {

    /**
     * Inyección de la dependencia a la clase EditorialPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaPuntosPersistence TarjetaPuntosPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPuntosEntity.class.getPackage())
                .addPackage(TarjetaPuntosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

     @Inject
    UserTransaction utx;
    
     /**
     * Configuración inicial de la prueba.
     *
     * 
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private List<TarjetaPuntosEntity> data = new ArrayList<>();
    private List<ClienteEntity> dataCliente = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            dataCliente.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            TarjetaPuntosEntity entity = factory.manufacturePojo(TarjetaPuntosEntity.class);
            if (i == 0) {
                entity.setCliente(dataCliente.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * 
     */
    private void clearData() {
        em.createQuery("delete from TarjetaPuntosEntity").executeUpdate();
    }
    
    /**
     * Prueba para crear una tarejeta de puntos.
     *
     *
     */
    @Test
    public void createTarjetaPuntosTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPuntosEntity newEntity = factory.manufacturePojo(TarjetaPuntosEntity.class);       
        TarjetaPuntosEntity result = TarjetaPuntosPersistence.create(newEntity);
        Assert.assertNotNull(result);
        TarjetaPuntosEntity entity = em.find(TarjetaPuntosEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

      /**
     * Devuelve todas las TarjetaPuntos de la base de datos.
     *
     * @return una lista con todas las TarjetaPuntos que encuentre en la base de
     * datos, "select u from TarjetaPuntosEntity u" es como un "select * from
     * TarjetaPuntosEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<TarjetaPuntosEntity> findAll() {
        // Se crea un query para buscar todas las TarjetaPuntos en la base de datos.
        TypedQuery query = em.createQuery("select u from TarjetaPuntosEntity u", TarjetaPuntosEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de TarjetaPuntos.
        return query.getResultList();
    }
    
    /**
     * Prueba para consultar la lista de TarjetaPuntos.
     *
     * 
     */
    
    @Test
    public void getTarjetaPuntoTest() {
        List<TarjetaPuntosEntity> list = TarjetaPuntosPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaPuntosEntity ent : list) {
            boolean found = false;
            for (TarjetaPuntosEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
      /**
     * Prueba para consultar un TarjetaPuntos.
     *
     * 
     */
    @Test
    public void getTarjetaPuntosTest() {
        TarjetaPuntosEntity entity = data.get(0);
        TarjetaPuntosEntity newEntity = TarjetaPuntosPersistence.find(dataCliente.get(0).getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getNumeroPuntos(), newEntity.getNumeroPuntos());
        
    }

      /**
     * Prueba para eliminar un TarjetaPuntos.
     *
     * 
     */
    @Test
    public void deleteTarjetaPuntosTest() {
        TarjetaPuntosEntity entity = data.get(0);
        TarjetaPuntosPersistence.delete(entity.getId());
        TarjetaPuntosEntity deleted = em.find(TarjetaPuntosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un TarjetaPuntos.
     *
     * 
     */
    @Test
    public void updateTarjetaPuntosTest() {
        TarjetaPuntosEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPuntosEntity newEntity = factory.manufacturePojo(TarjetaPuntosEntity.class);

        newEntity.setId(entity.getId());

        TarjetaPuntosPersistence.update(newEntity);

        TarjetaPuntosEntity resp = em.find(TarjetaPuntosEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
