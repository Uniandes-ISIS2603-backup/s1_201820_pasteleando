/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import javax.inject.Inject;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
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
 * @author dc.cepeda
 */
@RunWith(Arquillian.class)
public class DecoracionPersistenceTest {
    /**
     * Inyección de la dependencia a la clase DecoracionCatalogoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private DecoracionPersistence decoracionPersistence;
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Contexto transaciional
     */
    @Inject
            UserTransaction utx;
    /**
     * Atributo que modela una lista de Decoraciones del catálogo.
     */
    private List<DecoracionEntity> data = new ArrayList<>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Decoracion , el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionEntity.class.getPackage())
                .addPackage(DecoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from DecoracionPersonalizadaEntity").executeUpdate();
        em.createQuery("delete from DecoracionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DecoracionEntity entity = factory.manufacturePojo(DecoracionEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
     /**
     * Prueba para crear una Decoracion Catalogo.
     *
     *
     */
    @Test
    public void createDecoracionTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionEntity newEntity = factory.manufacturePojo(DecoracionEntity.class);
        DecoracionEntity result = decoracionPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        DecoracionEntity entity = em.find(DecoracionEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de decoraciones.
     *
     *
     */
    /**
    @Test
    public void getDecoracionesTest() {
        List<DecoracionEntity> list = decoracionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DecoracionEntity ent : list) {
            boolean found = false;
            for (DecoracionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    */
    
    /**
     * Prueba para consultar una decoración del catálogo.
     *
     *
     */
    @Test
    public void getDecoracionTest() {
        DecoracionEntity entity = data.get(0);
        DecoracionEntity newEntity = decoracionPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar una decoración del catálogo.
     *
     *
     */
    @Test
    public void updateDecoracionTest() {
        DecoracionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionEntity newEntity = factory.manufacturePojo(DecoracionEntity.class);
        
        newEntity.setId(entity.getId());
        
        decoracionPersistence.update(newEntity);
        
        DecoracionEntity resp = em.find(DecoracionEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una decoración.
     *
     *
     */
    @Test
    public void deleteDecoracionTest() {
        DecoracionEntity entity = data.get(0);
        decoracionPersistence.delete(entity.getId());
        DecoracionEntity deleted = em.find(DecoracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    }