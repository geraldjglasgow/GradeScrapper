import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class PhoneMessage {
    public static final String ACCOUNT_SID = "AC923cd4899b9f0cd671e40d07fa53da3b";
    public static final String AUTH_TOKEN = "1a64a67d3d14b8b6d60ce24720662c56";

    public void sendMessage(String text, String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(number),
                new PhoneNumber("+12692994028"),
                text)
                /*.setMediaUrl(
                        Arrays.asList(URI.create("https://demo.twilio.com/owl.png")))*/
                .create();

        System.out.println(message.getSid());
    }
}
