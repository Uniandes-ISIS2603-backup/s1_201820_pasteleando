/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.DecoracionPersonalizadaLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersonalizadaPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DecoracionPersonalizadaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DecoracionPersonalizadaLogic decoracionPersonalizadaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<DecoracionPersonalizadaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionPersonalizadaEntity.class.getPackage())
                .addPackage(DecoracionPersonalizadaLogic.class.getPackage())
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
        
        for (int i = 0; i < 3; i++) {
            DecoracionPersonalizadaEntity entity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
            em.persist(entity);
            data.add(entity);
            
        }
        
    }
    
    /**
     * Prueba para crear una decoración personalizada
     *
     *
     */
    @Test
    public void createDecoracionPersonalizadaTest() {
        try {
            DecoracionPersonalizadaEntity newEntity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
            DecoracionPersonalizadaEntity result = decoracionPersonalizadaLogic.createDecoracionPersonalizada(newEntity);
            Assert.assertNotNull(result);
            DecoracionPersonalizadaEntity entity = em.find(DecoracionPersonalizadaEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getFoto(), entity.getFoto());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(DecoracionPersonalizadaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para consultar la lista de decoraciones personalizadas
     *
     *
     */
    
    @Test
    public void getDecoracionesPersonalizadasTest() {
        List<DecoracionPersonalizadaEntity> list = decoracionPersonalizadaLogic.getDecoracionesPersonalizadas();
        Assert.assertEquals(data.size(), list.size());
        list.stream().map((entity) -> {
            boolean found = false;
            for (DecoracionPersonalizadaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            return found;
        }).forEachOrdered((found) -> {
            Assert.assertTrue(found);
        });
    }
    
    /**
     * Prueba para consultar una decoración personalizadas
     *
     *
     */
    @Test
    public void getDecoracionPersonalizadaTest() {
        DecoracionPersonalizadaEntity entity = data.get(0);
        DecoracionPersonalizadaEntity resultEntity = decoracionPersonalizadaLogic.getDecoracionPersonalizada(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getFoto(), resultEntity.getFoto());
    }
    
    /**
     * Prueba para actualizar una decoración personalizada.
     *
     *
     */
    @Test
    public void updateDecoracionPersonalizadaTest() {
        try {
            DecoracionPersonalizadaEntity entity = data.get(0);
            DecoracionPersonalizadaEntity pojoEntity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
            
            pojoEntity.setId(entity.getId());
            
            decoracionPersonalizadaLogic.updateDecoracionPersonalizada(pojoEntity.getId(),pojoEntity);
            
            DecoracionPersonalizadaEntity resp = em.find(DecoracionPersonalizadaEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getFoto(), resp.getFoto());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(DecoracionPersonalizadaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para eliminar una decoración personalizada.
     *
     *
     */
    @Test
    public void deleteDecoracionPersonalizadaTest() {
        DecoracionPersonalizadaEntity entity = data.get(0);
        decoracionPersonalizadaLogic.deleteDecoracionPersonalizada(entity.getId());
        DecoracionPersonalizadaEntity deleted = em.find(DecoracionPersonalizadaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
