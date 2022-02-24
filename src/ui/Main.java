package ui;
import app.Client;
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
		sc.close();
	}
}