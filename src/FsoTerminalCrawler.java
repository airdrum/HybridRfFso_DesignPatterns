import java.util.Arrays;

import utility.SshClass;

public class FsoTerminalCrawler {
	private static final boolean debug = false;
	public static void main(String[] args) {
			SshClass sshMngr = new SshClass("10.100.93.15", "pi","raspberry");
			sshMngr.sendCommand("ifconfig");
			String rfLines[]  = sshMngr.recvData().split("\\r?\\n");
			for (String str:rfLines)
				System.out.println(str);
			sshMngr.close();
	}
}
