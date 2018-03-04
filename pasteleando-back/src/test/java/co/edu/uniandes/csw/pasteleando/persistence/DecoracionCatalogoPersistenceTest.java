/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
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
 * @author jf.garcia
 */
@RunWith(Arquillian.class)
public class DecoracionCatalogoPersistenceTest {
    /**
     * Inyección de la dependencia a la clase DecoracionCatalogoPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private DecoracionCatalogoPersistence decoracionCatalogoPersistence;
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
    private List<DecoracionCatalogoEntity> data = new ArrayList<>();
    
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Decoracion Catalogo, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionCatalogoEntity.class.getPackage())
                .addPackage(DecoracionCatalogoPersistence.class.getPackage())
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
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear una Decoracion Catalogo.
     *
     *
     */
    @Test
    public void createDecoracionCatalogoTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionCatalogoEntity newEntity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
        DecoracionCatalogoEntity result = decoracionCatalogoPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        DecoracionCatalogoEntity entity = em.find(DecoracionCatalogoEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
    /**
     * Prueba para consultar la lista de decoraciones del catálogo.
     *
     *
     */
    @Test
    public void getDecoracionesCatalogoTest() {
        List<DecoracionCatalogoEntity> list = decoracionCatalogoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DecoracionCatalogoEntity ent : list) {
            boolean found = false;
            for (DecoracionCatalogoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una decoración del catálogo.
     *
     *
     */
    @Test
    public void getDecoracionCatalogoTest() {
        DecoracionCatalogoEntity entity = data.get(0);
        DecoracionCatalogoEntity newEntity = decoracionCatalogoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCategoria(), newEntity.getCategoria());
    }
    
    /**
     * Prueba para actualizar una decoración del catálogo.
     *
     *
     */
    @Test
    public void updateDecoracionCatalogoTest() {
        DecoracionCatalogoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionCatalogoEntity newEntity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
        
        newEntity.setId(entity.getId());
        
        decoracionCatalogoPersistence.update(newEntity);
        
        DecoracionCatalogoEntity resp = em.find(DecoracionCatalogoEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getCategoria(), resp.getCategoria());
    }
    
    /**
     * Prueba para eliminar una decoración del catálogo.
     *
     *
     */
    @Test
    public void deleteDecoracionCatalogoTest() {
        DecoracionCatalogoEntity entity = data.get(0);
        decoracionCatalogoPersistence.delete(entity.getId());
        DecoracionCatalogoEntity deleted = em.find(DecoracionCatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}