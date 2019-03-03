package throughput;

public interface Throughput {
	public static final String RF = "RF";
	public static final String FSO = "FSO";
	public static final String UDP = "UDP";
	public static final String TCP = "TCP";
	void setServer(String nodePlace);
	void setClient(String nodePlace);
}
