package net.snipersoft.hibernate

import org.apache.logging.log4j.kotlin.Logging
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence


class MyApp {
    companion object : Logging {
        @JvmStatic
        fun main(args: Array<String>) {
            val sessionFactory: EntityManagerFactory =
                Persistence.createEntityManagerFactory("main") //thread-safe, per db
            val entityManager = sessionFactory.createEntityManager() //JPA session, not thread-safe
            entityManager.transaction.begin()

            logger.info("Hello")

            entityManager.transaction.commit()
            entityManager.close()
            sessionFactory.close()
        }
    }
}

fun main(args: Array<String>) {

}

