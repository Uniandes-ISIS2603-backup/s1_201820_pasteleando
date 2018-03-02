/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
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
 * @author jf.garcia
 */
@RunWith(Arquillian.class)
public class PromocionPersistenceTest {
    /**
     * Inyección de la dependencia a la clase PromocionPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private PromocionPersistence PromocionPersistence;
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Contexto transaccional
     */
    @Inject
            UserTransaction utx;
    
    /**
     * Atributo que modela una lista de Decoraciones del catálogo.
     */
    private List<DecoracionCatalogoEntity> dataCatalogo = new ArrayList<>();
    
    /**
     * Atributo que modela una lista de Promociones.
     */
    private List<PromocionEntity> data = new ArrayList<>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Promocion, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PromocionEntity.class.getPackage())
                .addPackage(PromocionPersistence.class.getPackage())
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
        em.createQuery("delete from PromocionEntity").executeUpdate();
        em.createQuery("delete from DecoracionCatalogoEntity").executeUpdate();
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
            DecoracionCatalogoEntity entity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
            em.persist(entity);
            dataCatalogo.add(entity);
        }
        
        for (int i = 0; i < 3; i++) {
            PromocionEntity entity = factory.manufacturePojo(PromocionEntity.class);
            
            entity.setDecoracionCatalogo(dataCatalogo.get(1));
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear una Promocion.
     *
     *
     */
    @Test
    public void createPromocionTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        PromocionEntity newEntity = factory.manufacturePojo(PromocionEntity.class);
        PromocionEntity result = PromocionPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        PromocionEntity entity = em.find(PromocionEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar una promoción.
     *
     *
     */
    @Test
    public void getPromocionTest() {
        PromocionEntity entity = data.get(0);
        PromocionEntity newEntity = PromocionPersistence.find(dataCatalogo.get(0).getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCantidad(), newEntity.getCantidad());
    }
    
    /**
     * Prueba para actualizar una promoción.
     *
     *
     */
    @Test
    public void updatePromocionTest() {
        PromocionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PromocionEntity newEntity = factory.manufacturePojo(PromocionEntity.class);
        
        newEntity.setId(entity.getId());
        
        PromocionPersistence.update(newEntity);
        
        PromocionEntity resp = em.find(PromocionEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getCantidad(), resp.getCantidad());
    }
    
    /**
     * Prueba para eliminar una promoción.
     *
     *
     */
    @Test
    public void deletePromocionTest() {
        PromocionEntity entity = data.get(0);
        PromocionPersistence.delete(entity);
        PromocionEntity deleted = em.find(PromocionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
