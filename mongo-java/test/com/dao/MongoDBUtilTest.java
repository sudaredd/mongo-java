package com.dao;

import static org.junit.Assert.*;
import static com.dao.MongoDBUtil.*;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBUtilTest {

	@Test
	public void testClient() {
		MongoClient client = getMongoClient();
		assertNotNull(client);
	}
	@Test
	public void testConnection() {
		DB db = connection(getMongoClient());
		assertNotNull(db);
		assertTrue( db.getCollectionNames().size() >=2);
	}
	
	@Test
	public void testInsert() {
		 DBCollection coll = connection(getMongoClient()).getCollection("users");
		 long count = coll.getCount();
		 insert(coll);
		 assertEquals(count+1, coll.getCount());
	}
	
	private MongoClient getMongoClient() {
		try {
			return getClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
