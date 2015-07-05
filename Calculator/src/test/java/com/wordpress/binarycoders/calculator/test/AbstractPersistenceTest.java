package com.wordpress.binarycoders.calculator.test;

import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AbstractPersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static TestInjectorUtility injector;

    @BeforeClass
    public static void setupTest() throws Exception {

        AbstractPersistenceTest.entityManagerFactory = Persistence.createEntityManagerFactory("calculatorTestPU");
        AbstractPersistenceTest.entityManager = AbstractPersistenceTest.entityManagerFactory.createEntityManager();
        AbstractPersistenceTest.injector = new TestInjectorUtility();
        AbstractPersistenceTest.injector.assign(EntityManager.class, AbstractPersistenceTest.entityManager);

        AbstractPersistenceTest.entityManager.getTransaction().begin();
        final Scanner scanner = AbstractPersistenceTest.extracted().useDelimiter(";");
        while (scanner.hasNext()) {
            final String sql = scanner.next();
            AbstractPersistenceTest.executeSql(sql);
        }
        AbstractPersistenceTest.entityManager.getTransaction().commit();
    }

    private static Scanner extracted() {

        return new Scanner(AbstractPersistenceTest.class.getClassLoader().getResourceAsStream("META-INF/db-test.sql"));
    }

    public static void executeSql(final String sql) throws SQLException {

        AbstractPersistenceTest.entityManager.createNativeQuery(sql).executeUpdate();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

        AbstractPersistenceTest.entityManagerFactory.close();
    }

    public EntityManager getEntityManager() {

        return AbstractPersistenceTest.entityManager;
    }

    public <T> T entityManagerInjector(final T bean) throws Exception {

        AbstractPersistenceTest.injector.inject(bean);
        return bean;
    }

    public void startTransaction() {

        AbstractPersistenceTest.entityManager.getTransaction().begin();
    }

    public void endTransaction() {

        AbstractPersistenceTest.entityManager.getTransaction().commit();
    }
}
