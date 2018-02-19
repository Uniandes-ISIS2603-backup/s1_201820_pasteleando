/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionPersonalizadaEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
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
     * Inyección de la dependencia a la clase EditorialPersistence cuyos métodos
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
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DecoracionPersonalizadaEntity.class.getPackage())
                .addPackage(DecoracionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

/**
     * Prueba para crear un Editorial.
     *
     *
     */
    @Test
    public void createDecoracionTest() {
        
        
        PodamFactory factory = new PodamFactoryImpl();
        DecoracionPersonalizadaEntity newEntity = factory.manufacturePojo(DecoracionPersonalizadaEntity.class);
        DecoracionPersonalizadaEntity result = decoracionPersonalizadaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        DecoracionPersonalizadaEntity entity = em.find(DecoracionPersonalizadaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    }