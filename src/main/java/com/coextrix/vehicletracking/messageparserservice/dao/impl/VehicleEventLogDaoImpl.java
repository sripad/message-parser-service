package com.coextrix.vehicletracking.messageparserservice.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.coextrix.vehicletracking.messageparserservice.dao.IVehicleEventLogDao;
import com.coextrix.vehicletracking.messageparserservice.model.VehicleEventLog;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class VehicleEventLogDaoImpl implements IVehicleEventLogDao {

	public void insertVehicleEventLog(List<VehicleEventLog> vehicleEventLogs) {
		try (MongoClient mongoClient = new MongoClient("localhost", 27017);) {
			MongoDatabase database = mongoClient.getDatabase("VehicleTrackingDB");
			MongoCollection<Document> collection = database
					.getCollection("VehicleEventLog");
			List<Document> documents = new ArrayList<Document>();
			for (VehicleEventLog vehicleEventLog : vehicleEventLogs) {
				documents.add(vehicleEventLog.toDocument());
			}
			collection.insertMany(documents);
			mongoClient.close();
		}
	}
}
