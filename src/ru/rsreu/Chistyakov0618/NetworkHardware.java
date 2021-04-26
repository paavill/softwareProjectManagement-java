package ru.rsreu.Chistyakov0618;

import java.io.Serializable;
import java.util.Objects;

public class NetworkHardware implements Comparable<NetworkHardware>, Serializable {

	private static final long serialVersionUID = 862219469598676753L;
	private final String macAdress;
	private final String description;
	private final NetworkHardwareType type;

	public static final NetworkHardware NULL_OBJECT = new NetworkHardware() {

		private static final long serialVersionUID = -372321988503904765L;

		@Override
		public int compareTo(NetworkHardware o) {
			return 0;
		}

	};

	private NetworkHardware() {
		this.macAdress = "";
		this.description = "";
		this.type = NetworkHardwareType.NULL;
	}

	public NetworkHardware(String macAdress, String description, NetworkHardwareType type) {
		this.macAdress = macAdress;
		this.description = description;
		this.type = type;
	}

	public final String getMacAdress() {
		return macAdress;
	}

	public final String getDescription() {
		return description;
	}

	public final NetworkHardwareType getType() {
		return type;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.description).append(" ");
		result.append(this.macAdress).append(" ");
		result.append(this.type.getTypeName());
		return result.toString();
	}

	@Override
	public int compareTo(NetworkHardware o) {
		int result = this.type.compareTo(o.type);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || !(o instanceof NetworkHardware)) {
			return false;
		}
		return this.macAdress.equals(((NetworkHardware) o).macAdress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.description, this.macAdress, this.type);
	}

}
