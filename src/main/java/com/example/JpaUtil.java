package com.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    // Tên persistence-unit này phải khớp chính xác với tên cấu hình trong file persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "myAppPU"; 
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            try {
                // Khởi tạo EntityManagerFactory một lần duy nhất (Singleton)
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (Exception ex) {
                System.err.println("Lỗi khởi tạo EntityManagerFactory: " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}