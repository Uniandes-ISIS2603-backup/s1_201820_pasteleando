/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
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
 * @author dc.cepeda
 */
@RunWith(Arquillian.class)
public class DecoracionPersonalizadaPersistenceTest {
     /**
     * Inyección de la dependencia a la clase DecoracionPersonalizadaPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private DecoracionPersonalizadaPersistence decoracionPersonalizadaPersistence;
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
     * Atributo que modela una lista de Decoraciones del catálogo.
     */
    private List<DecoracionPersonalizadaEntity> data = new ArrayList<>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Decoracion Personalizada, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionPersonalizadaEntity.class.getPackage())
                .addPackage(DecoracionPersonalizadaPersistence.class.getPackage())
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
            DecoracionPersonalizadaEntity entity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
            
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
    public void createDecoracionPersonalizadaTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionPersonalizadaEntity newEntity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
        DecoracionPersonalizadaEntity result = decoracionPersonalizadaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        DecoracionPersonalizadaEntity entity = em.find(DecoracionPersonalizadaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de decoraciones del catálogo.
     *
     *
     */
     
    @Test
    public void getDecoracionesPersonalizadasTest() {
        List<DecoracionPersonalizadaEntity> list = decoracionPersonalizadaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DecoracionPersonalizadaEntity ent : list) {
            boolean found = false;
            for (DecoracionPersonalizadaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una decoración personalizada.
     *
     *
     */
    @Test
    public void getDecoracionPersonalizadaTest() {
        DecoracionPersonalizadaEntity entity = data.get(0);
        DecoracionPersonalizadaEntity newEntity = decoracionPersonalizadaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para actualizar una decoración del catálogo.
     *
     *
     */
    @Test
    public void updateDecoracionPersonalizadaTest() {
        DecoracionPersonalizadaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionPersonalizadaEntity newEntity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
        
        newEntity.setId(entity.getId());
        
        decoracionPersonalizadaPersistence.update(newEntity);
        
        DecoracionPersonalizadaEntity resp = em.find(DecoracionPersonalizadaEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
    /**
     * Prueba para eliminar una decoración.
     *
     *
     */
    @Test
    public void deleteDecoracionPersonalizadaTest() {
        DecoracionPersonalizadaEntity entity = data.get(0);
        decoracionPersonalizadaPersistence.delete(entity.getId());
        DecoracionPersonalizadaEntity deleted = em.find(DecoracionPersonalizadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para encontar una decoracion personalizada por el nombre
     */
    @Test
    public void findByNameTest()
    {
        DecoracionPersonalizadaEntity ent = data.get(0);
        DecoracionPersonalizadaEntity entEnc = decoracionPersonalizadaPersistence.findByName(ent.getName());
        Assert.assertEquals(ent, entEnc);
    }

    }