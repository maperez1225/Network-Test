package ui;
import app.Client;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		while(true) {
			String action = sc.nextLine();
			if (action.equalsIgnoreCase("close"))
				break;
			else
				client.executeAction(action);
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}