/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.FacturaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class FacturaPersistenceTest {
    /**
     * Inyección de la dependencia a la clase EditorialPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private FacturaPersistence FacturaPersistence;
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    
    @Inject
    UserTransaction utx;
    /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
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
            em.joinTransaction();
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
     * 
     */
    private List<FacturaEntity> data = new ArrayList<>();

    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            
            em.persist(entity);
            data.add(entity);
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
     * Prueba para crear un Editorial.
     *
     *
     */
    @Test
    public void createFacturaTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = FacturaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }
    
     /**
     * Devuelve todas las Factura de la base de datos.
     *
     * @return una lista con todas las facturas que encuentre en la base de
     * datos, "select u from FacturaEntity u" es como un "select * from
     * FacturaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<FacturaEntity> findAll() {
        // Se crea un query para buscar todas las facturas en la base de datos.
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de facturas.
        return query.getResultList();
    }
    
     /**
     * Prueba para consultar la lista de Factura.
     *
     * 
     */
    
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = FacturaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity ent : list) {
            boolean found = false;
            for (FacturaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
      /**
     * Prueba para consultar un Factura.
     *
     * 
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity newEntity = FacturaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDireccion(), newEntity.getDireccion());
        Assert.assertEquals(entity.getFecha(), newEntity.getFecha());
        Assert.assertEquals(entity.getHora(), newEntity.getHora());
    }

      /**
     * Prueba para eliminar un Factura.
     *
     * 
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaPersistence.delete(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Factura.
     *
     * 
     */
    @Test
    public void updateFacturaTest() {
        FacturaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);

        newEntity.setId(entity.getId());

        FacturaPersistence.update(newEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
    
}
