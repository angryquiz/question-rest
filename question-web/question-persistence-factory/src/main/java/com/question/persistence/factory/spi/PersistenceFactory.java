package com.question.persistence.factory.spi;

public class PersistenceFactory {
    public static Persistence getInstance(PersistenceType type) {
        Persistence persistence = null;
        switch (type) {
        case FILE:
        	persistence = new FilePersistence();
            break;
            
        case MYSQL:
        	persistence = new MySQLPersistence();
            break;
            
        case REDIS:
        	persistence = new RedisPersistence();
            break;            
            
        default:
            // throw some exception
            break;
        }
        return persistence;
    }
}