package org.tutorial.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("jpa-pu");
    }

    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }

}
