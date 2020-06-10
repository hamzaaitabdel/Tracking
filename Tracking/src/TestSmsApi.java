
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TestSmsApi {
	
	public static void main(String[] args) {
		String accountSid = "ACdd2d4c5d12f7aece341a436b576ffe27";
		String authToken = "511800d07b02c59ed98ea318ca5b8120";

		Twilio.init(accountSid, authToken);
		Message message = Message.creator(
			    new PhoneNumber("+212619860033"),  // To number
			    new PhoneNumber("+15559994321"),  // From number
			    "Hello world!"                    // SMS body
			).create();

			System.out.println(message.getSid());
	}
}
