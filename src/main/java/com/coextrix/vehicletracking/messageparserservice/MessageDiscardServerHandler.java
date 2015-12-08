package com.coextrix.vehicletracking.messageparserservice;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.List;

import com.coextrix.vehicletracking.messageparserservice.dao.IVehicleEventLogDao;
import com.coextrix.vehicletracking.messageparserservice.dao.impl.VehicleEventLogDaoImpl;
import com.coextrix.vehicletracking.messageparserservice.model.VehicleEventLog;

public class MessageDiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		IVehicleEventLogDao dao = new VehicleEventLogDaoImpl();
		StringBuilder messageReader = new StringBuilder();
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				messageReader.append((char) in.readByte());
			}
		} finally {
			ReferenceCountUtil.release(msg);
			String message = messageReader.toString();
			if (!validate(message)) {
				System.err.println("Invalid message");
				System.err.println(message);
				return;
			}
			System.out.println(message);
			List<VehicleEventLog> vahicleEventLogs = parseMessage(message);
			if (!vahicleEventLogs.isEmpty()) {
				dao.insertVehicleEventLog(vahicleEventLogs);
			}
		}
	}

	/*
	 * $<Packet Date>,<Packet Time>,<Gate ID 1>,<Tag Count 2>,<Tag ID 1
	 * (Hex)>,<Tag ID 1 Packet Time>,<Tag ID 2 (Hex)>, <Tag ID 2 Packet
	 * Time>,<Gate ID 2>,<Tag Count 3>,<Tag ID 3 (Hex)>,<Tag ID 3 Packet
	 * Time>,<Tag ID 4 (Hex)>, <Tag ID 4 Packet Time>,<Tag ID 1 (Hex)>,<Tag ID 1
	 * Packet Time>​#
	 */
	public List<VehicleEventLog> parseMessage(String message) {
		List<VehicleEventLog> vehicleEventLogs = new ArrayList<VehicleEventLog>();
		String data[] = message.substring(1, message.length() - 1).split(",");
		String packetDate = data[0];
		for (int i = 2; i < data.length; i++) {
			String readerGateID = data[i];
			i++;
			int tagCount = Integer.parseInt(data[i]);
			for (int j = 0; j < tagCount; j++) {
				String tagID = data[i + j * 2 + 1];
				String tagTime = data[i + j * 2 + 2];
				vehicleEventLogs.add(new VehicleEventLog(tagID, readerGateID,
						packetDate, tagTime));
			}
			i = i + tagCount * 2;
		}
		return vehicleEventLogs;
	}

	public boolean validate(String message) {
		return message.startsWith("$") && message.endsWith("#");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}