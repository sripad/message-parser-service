package com.coextrix.vehicletracking.messageparserservice.model;

public class RfidReader {
	private String readerGateId;
	private String latitude;
	private String longtitude;

	public RfidReader(String readerGateId, String latitude, String longtitude) {
		super();
		this.readerGateId = readerGateId;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public String getReaderGateId() {
		return readerGateId;
	}

	public void setReaderGateId(String readerGateId) {
		this.readerGateId = readerGateId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

}
