/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.FacturaLogic;
import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.FacturaPersistence;
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
 * @author m.leona
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private FacturaLogic factura;

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<FacturaEntity> data = new ArrayList<>();
    
    private List<TarjetaPuntosEntity> dataBook = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
     * 
     */
    private void clearData() {
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * 
     */
    private void insertData() {   
        
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Factura
     *
     * 
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException{
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = factura.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getHora(), entity.getHora());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Facturas
     *
     * 
     */
    
    @Test
    public void getFacturasTest() throws BusinessLogicException {
        List<FacturaEntity> list =factura.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
     
    /**
     * Prueba para consultar un Factura
     *
     * 
     */
    
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = factura.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getDireccion(), resultEntity.getDireccion());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        Assert.assertEquals(entity.getHora(), resultEntity.getHora());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Factura
     *
     * 
     * @throws co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException
     */
 
    @Test
    public void deleteFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        factura.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Factura
     *
     * 
     */
 @Test
    public void updateFacturaTest() throws BusinessLogicException{
         FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);

        pojoEntity.setId(entity.getId());

        factura.updateFactura(pojoEntity.getId(),pojoEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getDireccion(),resp.getDireccion());
        Assert.assertEquals(pojoEntity.getFecha(),resp.getFecha());
        Assert.assertEquals(pojoEntity.getHora(),resp.getHora());
    }
    
    @Test
    public void validatePrecio()
    {
        FacturaEntity entity = data.get(0);
        Integer integercito = entity.getPrecio();
        if(integercito > 0)
        {
            Assert.assertTrue(true);
        }
         else
        {
            Assert.assertTrue(false);
        }
    }
    
}
