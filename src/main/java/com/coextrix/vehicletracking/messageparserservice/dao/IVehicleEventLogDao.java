/**
 * 
 */
package com.coextrix.vehicletracking.messageparserservice.dao;

import java.util.List;

import com.coextrix.vehicletracking.messageparserservice.model.VehicleEventLog;

/**
 * @author sripad
 *
 */
public interface IVehicleEventLogDao {
	void insertVehicleEventLog(List<VehicleEventLog> vehicleEventLogs);
}

