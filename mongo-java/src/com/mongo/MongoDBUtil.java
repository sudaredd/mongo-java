package com.mongo;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBUtil {
	private static final Logger log = Logger.getLogger(MongoDBUtil.class);

	public static MongoClient getClient() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		mongoClient.getDatabaseNames().stream().forEach(log::info);
		return mongoClient;
	}

	public static DB connection(MongoClient mongoClient) {
		// get a db connection
		DB db = mongoClient.getDB("cvuser");
		// list objects
		log.info("objects");
		db.getCollectionNames().stream().forEach(log::info);
		return db;
	}

	public static void insert(DBCollection coll) {
		// insert a row
		BasicDBObject doc = new BasicDBObject("name", "darsan")
				.append("type", "emp").append("count", 1)
				.append("info", new BasicDBObject("x", 203).append("y", 102));
		coll.insert(doc);
	}

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = getClient();
		DB db = connection(mongoClient);
		DBCollection coll = db.getCollection("users");
		insert(coll);
		log.info("number of records:" + coll.getCount());

	}

}
