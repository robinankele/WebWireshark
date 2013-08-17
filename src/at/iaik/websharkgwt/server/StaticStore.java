package at.iaik.websharkgwt.server;

import java.util.List;
import java.util.Vector;

public class StaticStore {
	
	private static StaticStore staticStore;
	private List<String> greetings;
	
	public static StaticStore getInstance() {
		if (staticStore == null) {
			staticStore = new StaticStore();
		}
		return staticStore;
	}
	
	private StaticStore() {
		greetings = new Vector<String>();
	}
	
	public void addGreeting(String greeting) {
		greetings.add(greeting);
	}
	
	public List<String> getGreetings() {
		return greetings;
	}
}
