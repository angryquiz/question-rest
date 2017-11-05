package com.question.persistence.factory.client;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class App {
	public static void main(String[] args) {
		Persistence p = PersistenceFactory.getInstance(PersistenceType.REDIS);
	}

}
