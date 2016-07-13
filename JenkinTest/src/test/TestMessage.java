package test;

import junit.framework.TestCase;
import message.MessageGenerator;

public class TestMessage extends TestCase{

	public TestMessage(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void test_welcome_message() {
		MessageGenerator obj = new MessageGenerator();
		assertEquals("welcome", obj.getWelcomeMessage());
	}
 
}
