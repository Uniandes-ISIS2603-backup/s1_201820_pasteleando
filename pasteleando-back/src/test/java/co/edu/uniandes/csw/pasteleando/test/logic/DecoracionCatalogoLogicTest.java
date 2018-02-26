package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.DecoracionCatalogoLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionCatalogoPersistence;
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
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class DecoracionCatalogoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private DecoracionCatalogoLogic decoracionCatalogoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<DecoracionCatalogoEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionCatalogoEntity.class.getPackage())
                .addPackage(DecoracionCatalogoLogic.class.getPackage())
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
        em.createQuery("delete from DecoracionCatalogoEntity").executeUpdate();
        em.createQuery("delete from PromocionEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        
        for (int i = 0; i < 3; i++) {
            DecoracionCatalogoEntity entity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
            em.persist(entity);
            data.add(entity);
            
        }
        
    }
    
    /**
     * Prueba para crear una decoración del catálogo
     *
     *
     */
    @Test
    public void createDecoracionCatalogoTest() {
        try {
            DecoracionCatalogoEntity newEntity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
            DecoracionCatalogoEntity result = decoracionCatalogoLogic.createDecoracionCatalogo(newEntity);
            Assert.assertNotNull(result);
            DecoracionCatalogoEntity entity = em.find(DecoracionCatalogoEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getCategoria(), entity.getCategoria());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(DecoracionCatalogoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para consultar la lista de decoraciones del catálogo
     *
     *
     */
    @Test
    public void getDecoracionesCatalogoTest() {
        List<DecoracionCatalogoEntity> list = decoracionCatalogoLogic.getDecoracionesCatalogo();
        Assert.assertEquals(data.size(), list.size());
        list.stream().map((entity) -> {
            boolean found = false;
            for (DecoracionCatalogoEntity storedEntity : data) {
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
     * Prueba para consultar una decoración del catálogo
     *
     *
     */
    @Test
    public void getDecoracionCatalogoTest() {
        DecoracionCatalogoEntity entity = data.get(0);
        DecoracionCatalogoEntity resultEntity = decoracionCatalogoLogic.getDecoracionCatalogo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCategoria(), resultEntity.getCategoria());
    }
    
    /**
     * Prueba para actualizar una decoración del catálogo.
     *
     *
     */
    @Test
    public void updateDecoracionCatalogoTest() {
        try {
            DecoracionCatalogoEntity entity = data.get(0);
            DecoracionCatalogoEntity pojoEntity = factory.manufacturePojo(DecoracionCatalogoEntity.class);
            
            pojoEntity.setId(entity.getId());
            
            decoracionCatalogoLogic.updateDecoracionCatalogo(pojoEntity.getId(),pojoEntity);
            
            DecoracionCatalogoEntity resp = em.find(DecoracionCatalogoEntity.class, entity.getId());
            
            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getCategoria(), resp.getCategoria());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(DecoracionCatalogoLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para eliminar una decoración del catálogo.
     *
     *
     */
    @Test
    public void deleteDecoracionCatalogoTest() {
        DecoracionCatalogoEntity entity = data.get(0);
        decoracionCatalogoLogic.deleteDecoracionCatalogo(entity.getId());
        DecoracionCatalogoEntity deleted = em.find(DecoracionCatalogoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
