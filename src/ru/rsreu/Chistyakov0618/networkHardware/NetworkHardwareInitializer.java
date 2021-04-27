package ru.rsreu.Chistyakov0618.networkHardware;

public class NetworkHardwareInitializer {

	private static final NetworkHardware[] NETWORK_HARDWARE = {
			new NetworkHardware("B9:29:9B:E6:0D:69", "Location: 6. room", NetworkHardwareType.REPEATER),
			new NetworkHardware("59:F6:47:42:69:ED", "Location: 6. room", NetworkHardwareType.BRIDGE),
			new NetworkHardware("69:06:9A:03:95:58", "Location: 5. room", NetworkHardwareType.ROUTER),
			new NetworkHardware("F1:CA:11:0E:9C:D6", "Location: 9. room", NetworkHardwareType.REPEATER) };

	private NetworkHardwareInitializer() {

	}

	public static NetworkHardware[] initializeNetworkHardwares() {
		return NetworkHardwareInitializer.NETWORK_HARDWARE;
	}
}
