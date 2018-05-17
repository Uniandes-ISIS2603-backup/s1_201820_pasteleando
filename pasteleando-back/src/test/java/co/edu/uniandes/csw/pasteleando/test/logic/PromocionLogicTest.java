package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.PromocionLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
import co.edu.uniandes.csw.pasteleando.entities.PromocionEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.PromocionPersistence;
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
public class PromocionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PromocionLogic PromocionLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PromocionEntity> data = new ArrayList<>();
    
    private List<DecoracionCatalogoEntity> dataCatalogo = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PromocionEntity.class.getPackage())
                .addPackage(PromocionLogic.class.getPackage())
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
     * Prueba para crear una promoción
     *
     *
     */
    @Test
    public void createPromocionTest() throws BusinessLogicException {
        PromocionEntity newEntity = factory.manufacturePojo(PromocionEntity.class);
        PromocionEntity result = PromocionLogic.createPromocion(data.get(0).getDecoracionCatalogo().getId(), newEntity);
        Assert.assertNotNull(result);
        PromocionEntity entity = em.find(PromocionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());

        Assert.assertEquals(newEntity.getCantidad(), entity.getCantidad());

    }

    /**
     * Prueba para consultar la lista de Promociones.
     *
     *
     */
    @Test
    public void getPromocionesTest() throws BusinessLogicException {
        List<PromocionEntity> list = PromocionLogic.getPromociones(dataCatalogo.get(1).getId());
        //Assert.assertEquals(data.size(), list.size());
        for (PromocionEntity entity : list) {
            boolean found = false;
            for (PromocionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una promoción.
     *
     *
     */
    @Test
    public void getPromocionTest() {
        PromocionEntity entity = data.get(0);
        PromocionEntity resultEntity = PromocionLogic.getPromocion(dataCatalogo.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getCantidad(), resultEntity.getCantidad());
    }

    /**
     * Prueba para actualizar una promoción.
     *
     *
     */
    @Test
    public void updatePromocionTest() throws BusinessLogicException {
        PromocionEntity entity = data.get(0);
        PromocionEntity pojoEntity = factory.manufacturePojo(PromocionEntity.class);

        pojoEntity.setId(entity.getId());

        PromocionLogic.updatePromocion(dataCatalogo.get(1).getId(), pojoEntity);

        PromocionEntity resp = em.find(PromocionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getCantidad(), resp.getCantidad());
    }

    /**
     * Prueba para eliminar una promoción.
     *
     *
     */
    @Test
    public void deletePromocionTest() {
        PromocionEntity entity = data.get(0);
        PromocionLogic.deletePromocion(dataCatalogo.get(1).getId(), entity.getId());
        PromocionEntity deleted = em.find(PromocionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
