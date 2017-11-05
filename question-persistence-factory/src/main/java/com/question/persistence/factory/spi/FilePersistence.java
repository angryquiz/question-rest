package com.question.persistence.factory.spi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class FilePersistence extends Persistence {

	private static Logger LOG = Logger.getLogger(FilePersistence.class);

	FilePersistence() {
		super(PersistenceType.FILE);
		construct();
	}

	@Override
	protected void construct() {
		LOG.info("Building file persistence");
	}

	@Override
	public boolean saveData(String memberNumber, String sessionId, Object obj) {

		boolean ret = true;
		FileOutputStream fout = null;
		try {
			String temp = System.getProperty("java.io.tmpdir");
			fout = new FileOutputStream(temp + "/memorizer-" + memberNumber + "-" + sessionId + ".tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(obj);

		} catch (IOException e) {
			LOG.error("IOException: " + e.getMessage().toString());
			ret = false;
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
				ret = false;
			}
		}
		return ret;
	}

	@Override
	public Object retrieveData(String memberNumber, String sessionId) {
		try {
			String temp = System.getProperty("java.io.tmpdir");
			FileInputStream fin = new FileInputStream(temp + "/memorizer-" + memberNumber + "-" + sessionId + ".tmp");
			ObjectInputStream ois = new ObjectInputStream(fin);
			return ois.readObject();
		} catch (Exception e) {
			LOG.error("IOException: " + e.getMessage().toString());
		}
		return null;
	}
}