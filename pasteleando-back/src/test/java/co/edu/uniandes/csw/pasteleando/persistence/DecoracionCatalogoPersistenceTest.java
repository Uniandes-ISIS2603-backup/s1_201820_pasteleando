/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.pasteleando.persistence;

import co.edu.uniandes.csw.pasteleando.entities.DecoracionCatalogoEntity;
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

    }