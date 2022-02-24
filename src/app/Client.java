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
	private long a;
	private static final String ping = new String(new byte[1024]);
	private static final String data = new String(new byte[8192]);
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
		try {
			a = System.nanoTime();
			br.write(data+"\n");
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void pingTest() {
		try {
			a = System.currentTimeMillis();
			br.write(ping+"\n");
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void getTime() {
		try {
			br.write("whatTimeIsIt\n");
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public void close() throws IOException {
		socket.close();
	}
	@Override
	public void onMessage(String m) {
		if (m.equals(ping))
			System.out.println(System.currentTimeMillis()-a+" ms");
		else if (m.equals(data))
			System.out.println((int)(8/((double)(System.nanoTime()-a)*Math.pow(10, -9)))+" KB/s");
		else
		System.out.println(m);
	}
}