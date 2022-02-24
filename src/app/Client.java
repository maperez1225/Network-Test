package app;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.Receiver;
import com.Receiver.OnMessageListener;
public class Client implements OnMessageListener {
	private Socket socket;
	private OutputStream os;
	private BufferedWriter br;
	private Receiver receiver;
	public Client() {
		try {
			socket = new Socket("127.0.0.1",8080);
			os = socket.getOutputStream();
			br = new BufferedWriter(new OutputStreamWriter(os));
			receiver = new Receiver(socket.getInputStream());
			receiver.start();
			receiver.setListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void executeAction(String action) {
		if (action.equalsIgnoreCase("remoteIpconfig"))
			ipConfig();
		else if (action.equalsIgnoreCase("interface"))
			getInterface();
		else if (action.equalsIgnoreCase("whatTimeIsIt"))
			getTime();
		else if (action.equalsIgnoreCase("RTT"))
			pingTest();
		else if (action.equalsIgnoreCase("speed"))
			speedTest();
		else
			System.out.println("Invalid action.");
	}
	private void speedTest() {
		// TODO Auto-generated method stub
		
	}
	private void pingTest() {
		// TODO Auto-generated method stub
		
	}
	private void getTime() {
		// TODO Auto-generated method stub
		
	}
	private void getInterface() {
		try {
			br.write("interface\n");
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void ipConfig() {
		try {
			br.write("remoteIpconfig\n");
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onMessage(String m) {
		System.out.println(m);
	}
}