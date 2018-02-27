/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.DecoracionLogic;
import co.edu.uniandes.csw.pasteleando.entities.DecoracionEntity;
import co.edu.uniandes.csw.pasteleando.entities.PastelEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.DecoracionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class DecoracionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private DecoracionLogic decoracionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DecoracionEntity> data = new ArrayList<DecoracionEntity>();

    private List<PastelEntity> pastelesData = new ArrayList();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionEntity.class.getPackage())
                .addPackage(DecoracionLogic.class.getPackage())
                .addPackage(DecoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     */
    private void clearData() {
        em.createQuery("delete from PastelEntity").executeUpdate();
        em.createQuery("delete from DecoracionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PastelEntity pasteles = factory.manufacturePojo(PastelEntity.class);
            em.persist(pasteles);
            pastelesData.add(pasteles);
        }
        for (int i = 0; i < 3; i++) {
            DecoracionEntity entity = factory.manufacturePojo(DecoracionEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                pastelesData.get(i).setDecoracion(entity);
            }
        }
    }

    /**
     * Prueba para crear un Decoracion
     *
     * @throws co.edu.uniandes.csw.pastelestore.exceptions.BusinessLogicException
     */
    @Test
    public void createDecoracionTest() throws BusinessLogicException {
        DecoracionEntity newEntity = factory.manufacturePojo(DecoracionEntity.class);
        DecoracionEntity result = decoracionLogic.createDecoracion(newEntity);
        Assert.assertNotNull(result);
        DecoracionEntity entity = em.find(DecoracionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Decoracions
     *
     * 
     */
    @Test
    public void getDecoracionsTest() {
        List<DecoracionEntity> list = decoracionLogic.getDecoraciones();
        Assert.assertEquals(data.size(), list.size());
        for (DecoracionEntity entity : list) {
            boolean found = false;
            for (DecoracionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Decoracion
     *
     * 
     */
    @Test
    public void getDecoracionTest() {
        DecoracionEntity entity = data.get(0);
        DecoracionEntity resultEntity = decoracionLogic.getDecoracion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Decoracion
     *
     * 
     */
    /*
    @Test
    public void deleteDecoracionTest() throws BusinessLogicException {
        DecoracionEntity entity = data.get(0);
        decoracionLogic.removePastel(pastelesData.get(0).getId(), entity.getId());
        decoracionLogic.deleteDecoracion(entity.getId());
        DecoracionEntity deleted = em.find(DecoracionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    */

    /**
     * Prueba para actualizar un Decoracion
     *
     * 
     */
    @Test
    public void updateDecoracionTest() {
        DecoracionEntity entity = data.get(0);
        DecoracionEntity pojoEntity = factory.manufacturePojo(DecoracionEntity.class);

        pojoEntity.setId(entity.getId());

        decoracionLogic.updateDecoracion(pojoEntity.getId(), pojoEntity);

        DecoracionEntity resp = em.find(DecoracionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }

    /**
     * Prueba para obtener una instancia de Pastels asociada a una instancia
     * Decoracion
     *
     * 
     */
    /*
    @Test
    public void getPastelsTest() throws BusinessLogicException {
        DecoracionEntity entity = data.get(0);
        PastelEntity bookEntity = pastelesData.get(0);
        PastelEntity response = decoracionLogic.getPastel(entity.getId(), bookEntity.getId());

        Assert.assertEquals(bookEntity.getId(), response.getId());
        Assert.assertEquals(bookEntity.getName(), response.getName());
        Assert.assertEquals(bookEntity.getDescription(), response.getDescription());
        Assert.assertEquals(bookEntity.getIsbn(), response.getIsbn());
        Assert.assertEquals(bookEntity.getImage(), response.getImage());
    }
*/
    /**
     * Prueba para obtener una colección de instancias de Pastels asociadas a una
     * instancia Decoracion
     *
     * 
     */
    @Test
    public void listPastelsTest() {
        List<PastelEntity> list = decoracionLogic.listPastels(data.get(0).getId());
        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para asociar un Pastels existente a un Decoracion
     *
     * 
     */
    /*
    @Test
    public void addPastelsTest() {
        DecoracionEntity entity = data.get(0);
        PastelEntity bookEntity = pastelesData.get(1);
        PastelEntity response = decoracionLogic.addPastel(bookEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(bookEntity.getId(), response.getId());
    }
*/
    /**
     * Prueba para remplazar las instancias de Pastels asociadas a una instancia
     * de Decoracion
     *
     * 
     */
    /*
    @Test
    public void replacePastelsTest() {
        DecoracionEntity entity = data.get(0);
        List<PastelEntity> list = pastelesData.subList(1, 3);
        decoracionLogic.replacePastels(entity.getId(), list);

        entity = decoracionLogic.getDecoracion(entity.getId());
        Assert.assertFalse(entity.getPastels().contains(pastelesData.get(0)));
        Assert.assertTrue(entity.getPastels().contains(pastelesData.get(1)));
        Assert.assertTrue(entity.getPastels().contains(pastelesData.get(2)));
    }
*/
    /**
     * Prueba para desasociar un Pastel existente de un Decoracion existente
     *
     * 
     */
    /*
    @Test
    public void removePastelsTest() throws BusinessLogicException {
        try {
            decoracionLogic.removePastel(data.get(0).getId(), pastelesData.get(0).getId());
            PastelEntity response = decoracionLogic.getPastel(data.get(0).getId(), pastelesData.get(0).getId());
        } catch (BusinessLogicException e) {
        }

    }
*/
}
