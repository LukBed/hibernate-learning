package net.snipersoft.hibernate;

import net.snipersoft.hibernate.entity.Product;
import net.snipersoft.hibernate.entity.ProductType;
import net.snipersoft.hibernate.entity.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RelationsApp {
    private static final Logger logger = LogManager.getLogger(RelationsApp.class);

    public static void main(String[] args) {
        EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("main"); //thread-safe, per db
        EntityManager entityManager = sessionFactory.createEntityManager(); //JPA session, not thread-safe

//        readProducts(entityManager);
//        readReviews(entityManager);
        deleteProductAndReviews(entityManager);

        entityManager.close();
        sessionFactory.close();
    }

    static void readProducts(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        List<Product> result = entityManager.createQuery("SELECT p FROM Product p").getResultList();
        result.forEach(p -> {
            //in debugger we won't see reviews are lazy load, because debugger calls toString, but if we launch app at normal mode we'll see it in logs
            logger.info(p.getName());
            //select reviews
            logger.info(p.getReviews());
        });

        entityManager.getTransaction().commit();
    }

    static void readReviews(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        List<Review> result = entityManager.createQuery("SELECT r FROM Review r").getResultList();
        result.forEach(r -> {
            logger.info(r.getContent());
            logger.info(r.getProduct().getName());
        });

        entityManager.getTransaction().commit();
    }

    static void deleteProductAndReviews(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        //Postgres supports ON DELETE CASCADE
        Product product = entityManager.find(Product.class, 1);
        entityManager.remove(product);

        entityManager.getTransaction().commit();
    }
}
