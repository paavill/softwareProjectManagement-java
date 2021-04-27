package ru.rsreu.Chistyakov0618.networkHardware;

import com.prutzkow.resourcer.Resourcer;

public enum NetworkHardwareType {
	REPEATER(Resourcer.getString("hardware.network.type.repeater")),
	CONCENTRATOR(Resourcer.getString("hardware.network.type.concentrator")),
	BRIDGE(Resourcer.getString("hardware.network.type.bridge")),
	SWITCH(Resourcer.getString("hardware.network.type.switch")),
	ROUTER(Resourcer.getString("hardware.network.type.router")),
	NULL(Resourcer.getString("hardware.network.type.null"));
	
	private final String typeName;
	
	NetworkHardwareType(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
}
