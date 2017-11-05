package com.question.persistence.factory.spi;

import org.apache.log4j.Logger;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;

public class RedisPersistence extends Persistence {

	private static Logger LOG = Logger.getLogger(RedisPersistence.class);

	Jedis jedis = null;

	RedisPersistence() {
		super(PersistenceType.REDIS);
		construct();
	}

	@Override
	protected void construct() {

		// https://xicojunior.wordpress.com/2012/07/01/getting-started-with-jedis/

		String redisServer = System.getProperty("redisServer");
		String redisPort = System.getProperty("redisPort");

		if (StringUtils.isEmpty(redisServer)) {
			redisServer = "localhost";
		}

		if (StringUtils.isEmpty(redisPort)) {
			redisPort = "6379";
		}
		
		LOG.info("Building REDIS persistence");

		LOG.info("redisServer: " + redisServer);
		LOG.info("redisPort: " + redisPort);

		jedis = new Jedis(redisServer, Integer.valueOf(redisPort));

	}

	@Override
	public boolean saveData(String memberNumber, String sessionId, Object obj) {

		String cacheKey = memberNumber + "-" + sessionId;
		LOG.info("set cacheKey=" + cacheKey);

		jedis.set(cacheKey.getBytes(), SerializationUtils.serialize(obj));

		// setting the TTL in seconds
		jedis.expire(cacheKey, 10800); // 10800 is 3 hours.

		boolean ret = true;

		return ret;
	}

	@Override
	public Object retrieveData(String memberNumber, String sessionId) {

		String cacheKey = memberNumber + "-" + sessionId;
		LOG.info("get cacheKey=" + cacheKey);

		byte[] obj = jedis.get(cacheKey.getBytes());
		Object o = SerializationUtils.deserialize(obj);
		return o;
	}
}