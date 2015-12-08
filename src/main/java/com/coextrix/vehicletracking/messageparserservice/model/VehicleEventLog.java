package com.coextrix.vehicletracking.messageparserservice.model;

import java.sql.Timestamp;

import org.bson.BsonDateTime;
import org.bson.Document;

public class VehicleEventLog {
	private String tagId;
	private String readerGateId;
	private Timestamp packetDateTime;

	public VehicleEventLog(String tagID, String readerGateID,
			String packetDate, String tagTime) {
		super();
		this.tagId = tagID;
		this.readerGateId = readerGateID;
		int year = Integer.parseInt(packetDate.substring(0, 4));
		int month = Integer.parseInt(packetDate.substring(4, 6));
		int date = Integer.parseInt(packetDate.substring(6));
		int hour = Integer.parseInt(tagTime.substring(0, 2));
		int minute = Integer.parseInt(tagTime.substring(2, 4));
		int second = Integer.parseInt(tagTime.substring(4));
		int nano = 0;
		this.packetDateTime = Timestamp.valueOf(year + "-" + month + "-" + date
				+ " " + hour + ":" + minute + ":" + second + "." + nano);
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagID) {
		this.tagId = tagID;
	}

	public String getReaderGateId() {
		return readerGateId;
	}

	public void setReaderGateId(String readerGateID) {
		this.readerGateId = readerGateID;
	}

	public Timestamp getPacketDateTime() {
		return packetDateTime;
	}

	public void setPacketDateTime(Timestamp packetDateTime) {
		this.packetDateTime = packetDateTime;
	}

	public Document toDocument() {
		Document document = new Document("tagId", this.getTagId());
		document.append("readerGateId", this.getReaderGateId());
		document.append("packetDateTime", new BsonDateTime(this.getPacketDateTime().getTime()));
		return document;
	}

}
