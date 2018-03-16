/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.test.logic;

import co.edu.uniandes.csw.pasteleando.ejb.TarjetaPuntosLogic;
import co.edu.uniandes.csw.pasteleando.entities.ClienteEntity;
import co.edu.uniandes.csw.pasteleando.entities.TarjetaPuntosEntity;
import co.edu.uniandes.csw.pasteleando.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pasteleando.persistence.TarjetaPuntosPersistence;
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
public class TarjetaPuntosLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TarjetaPuntosLogic tarjeta;

    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<TarjetaPuntosEntity> data = new ArrayList<>();
    
    private List<ClienteEntity> dataCliente = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPuntosEntity.class.getPackage())
                .addPackage(TarjetaPuntosLogic.class.getPackage())
                .addPackage(TarjetaPuntosPersistence.class.getPackage())
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
        em.createQuery("delete from TarjetaPuntosEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * 
     */
    private void insertData() {      
        
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
     * Prueba para crear un TarjetaPuntos
     *
     * 
     */
    @Test
    public void createTarjetaPuntosTest() throws BusinessLogicException{
        TarjetaPuntosEntity newEntity = factory.manufacturePojo(TarjetaPuntosEntity.class);
        TarjetaPuntosEntity result = tarjeta.createTarjetaPuntos(dataCliente.get(0).getId(),newEntity);
        Assert.assertNotNull(result);
        TarjetaPuntosEntity entity = em.find(TarjetaPuntosEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getNumeroPuntos(), entity.getNumeroPuntos());
    }

     
    /**
     * Prueba para consultar un TarjetaPuntos
     *
     * 
     */
    
    @Test
    public void getTarjetaPuntosTest() {
        TarjetaPuntosEntity entity = data.get(0);
        TarjetaPuntosEntity resultEntity = tarjeta.getTarjetaPuntos(dataCliente.get(0).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNumeroPuntos(), resultEntity.getNumeroPuntos());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un TarjetaPuntos
     *
     * 
     */
 
    @Test
    public void deleteTarjetaPuntosTest() {
        TarjetaPuntosEntity entity = data.get(0);
        
        tarjeta.deleteTarjetaPuntos(dataCliente.get(1).getId(),entity.getId());
        TarjetaPuntosEntity deleted = em.find(TarjetaPuntosEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un TarjetaPuntos
     *
     * 
     */
 @Test
    public void updateTarjetaPuntosTest() throws BusinessLogicException{
        TarjetaPuntosEntity entity = data.get(0);
        TarjetaPuntosEntity pojoEntity = factory.manufacturePojo(TarjetaPuntosEntity.class);

        pojoEntity.setId(entity.getId());

        tarjeta.updateTarjetaPuntos(dataCliente.get(1).getId(),pojoEntity);

        TarjetaPuntosEntity resp = em.find(TarjetaPuntosEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
       Assert.assertEquals(pojoEntity.getNumeroPuntos(), resp.getNumeroPuntos());
    }
    
     @Test
    public void validateNumeropuntos()
    {
        TarjetaPuntosEntity entity = data.get(0);
        Integer integercito = entity.getNumeroPuntos();
        if(integercito >= 0)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }
    }
}
