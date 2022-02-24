package app;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import com.Receiver.OnMessageListener;
import com.ServerConnection;
public class Server implements OnMessageListener{
	private ServerConnection connection;
	public Server() {
		connection = ServerConnection.getInstance();
		connection.setPort(8080);
		connection.setListener(this);
	}
	public void init() {
		connection.start();
	}
	@Override
	public void onMessage(String m) {
		try {
			executeAction(m);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void executeAction(String m) throws IOException {
		if (m.equalsIgnoreCase("remoteIpconfig")) {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for(NetworkInterface i:interfaces) {
				if (i.getHardwareAddress() != null) {
					List<InetAddress> ips = Collections.list(i.getInetAddresses());
					for(InetAddress j:ips) {
						if (j instanceof Inet4Address)
							connection.sendMessage(j.toString());
					}
				}
			}
		}else if (m.equalsIgnoreCase("interface")) {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for(NetworkInterface i:interfaces) {
				if (i.getHardwareAddress() != null) {
					List<InetAddress> ips = Collections.list(i.getInetAddresses());
					for(InetAddress j:ips) {
						if (j instanceof Inet4Address)
							connection.sendMessage(i.getDisplayName());
					}
				}
			}
		}else if (m.equalsIgnoreCase("whatTimeIsIt")) {
			
		}else if (m.equalsIgnoreCase("RTT")) {
			
		}else if (m.equalsIgnoreCase("speed")) {
			
		}
	}
}