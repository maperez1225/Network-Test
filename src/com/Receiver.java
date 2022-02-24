package com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class Receiver extends Thread {
	private InputStream is;
	public OnMessageListener listener;
	public Receiver(InputStream i) {
		is = i;
	}
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (true) {
				String msg = br.readLine();
				if (msg == null)
					break;
				listener.onMessage(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setListener(OnMessageListener l) {
		listener = l;
	}
	public interface OnMessageListener {
		public void onMessage(String m);
	}
}