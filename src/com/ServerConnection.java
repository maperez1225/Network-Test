package com;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.Receiver.OnMessageListener;
public class ServerConnection extends Thread {
	private static ServerConnection instance = null;
	private ServerConnection() {
	}
	public static synchronized ServerConnection getInstance() {
		if (instance == null)
			instance = new ServerConnection();
		return instance;
	}
	private ServerSocket server;
	private Socket socket;
	private int port;
	private Receiver receiver;
	private OnMessageListener listener;
	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			receiver = new Receiver(socket.getInputStream());
			receiver.setListener(listener);
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPort(int p) {
		port = p;
	}
	public void setListener(OnMessageListener l) {
		listener = l;
	}
	public void sendMessage(String string) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		bw.write(string+"\n");
		bw.flush();
	}
}