package net.snipersoft.hibernate;

import net.snipersoft.hibernate.entity.Product;
import net.snipersoft.hibernate.entity.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CrudApp {
    private static final Logger logger = LogManager.getLogger(CrudApp.class);

    public static void main(String[] args) {
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("main"); //thread-safe, per db
        EntityManager entityManager = sessionFactory.createEntityManager(); //JPA session, not thread-safe

        saveProduct(entityManager);
//        readProduct(entityManager);
//        updateProduct(entityManager);
//        updateWithoutReading(entityManager);
//        removeProduct(entityManager);

        entityManager.close();
        sessionFactory.close();
    }

    static void saveProduct(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setName("T-34");
        product.setDescription("This is a model T-34-85");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("19.99"));
        product.setProductType(ProductType.REAL);

        logger.info(product);
        entityManager.persist(product);

        entityManager.getTransaction().commit();
    }

    static void readProduct(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        logger.info(product);

        entityManager.getTransaction().commit();
    }

    static void updateProduct(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        product.setName("T-34-85"); //dirty checking, update will be executed only if field was changed
//        Product merged = entityManager.merge(product); //update will be executed after merge or commit
//        logger.info(merged);

        entityManager.getTransaction().commit();
    }

    static void updateWithoutReading(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Product product = new Product(); //detached
        product.setId(1);
        product.setName("T-43-85 (mod)");
        Product merged = entityManager.merge(product); //select will be executed, all not defined fields will be set ot be null!
        logger.info(merged);

        entityManager.getTransaction().commit();
    }

    static void removeProduct(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Product product = entityManager.find(Product.class, 1);
        entityManager.remove(product);

        entityManager.getTransaction().commit();
    }
}
