package org.cst8288.finalproject.manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.cst8288.finalproject.subscriptions.Alert;
import org.cst8288.finalproject.subscriptions.Subscriber;
import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.enums.ContactMethod;

public class SubscriptionManager extends SubscriberDAO{
	private SubscriberDAO subscriberDAO;
	private List<Subscriber> subscribers;
	private Alert alertMessage;
	
	// observer pattern requirements 
	private List<Observer> observers = new ArrayList<>();
	private int threshold;
	private int databaseValue;
	
	//Email config for javamail
    private String host = "smtp.gmail.com";
    private int port = 587;
    private String username = "algonquinfwrp@gmail.com";
    private String password = "ujfurdzynscpgndi";
    private String recipent;
    
    private String subject;
    private String body;
    
    // Config for sms using Twilio
    public static final String ACCOUNT_SID = "ACdcac2e2eb8856fcda97bddcfa5adb047";
    public static final String AUTH_TOKEN = "3850c2c34dd7f057a51aa4546d4e5787";
			
	public SubscriptionManager(SubscriberDAO subscriberDAO) {
		
	}
	
	public void subscribe(Subscriber subscriber) {
		createSubscriber(subscriber);
	}
	
	public void unsubscribe(int subscriberID) {
        deleteSubscriber(subscriberID);
	}
	
	public List<Subscriber> listSubs(){
		return listSubscribers();
	}

	public void updateSubsContactInfo(int subID, ContactMethod contactMethod, String contactInfo) {
		updateSubscriber(subID, contactMethod, contactInfo);
	}
	
	public Subscriber getSubDetails(int subId){
		return retrieveSubscriber(subId);
	}
	
	public void notifySubs(Alert alert) {
		subscribers = listSubs();
		for(Subscriber sub : subscribers) {
			sendAlertToSubscriber(sub, alert);
		}
	}
	
	public void sendAlertToSubscriber(Subscriber subscriber, Alert alert) {
	    //JavaMail properties
	    Properties mailProperties = new Properties();
	    mailProperties.put("mail.smtp.host", host);
	    mailProperties.put("mail.smtp.port", port);
	    mailProperties.put("mail.smtp.auth", "true");
	    mailProperties.put("mail.smtp.starttls.enable", "true");
	    
	    // authenticator for sending the email
	    Authenticator authenticator = new Authenticator() {
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(username, password);
	    	}
	    };
	    
	    // session with authenticator's properties
	    Session emailSession = Session.getInstance(mailProperties, authenticator);
		
		ContactMethod method = subscriber.getMethod();
		
		switch(method) {
			case EMAIL:
				try {
		            // Create MimeMessage object
		            MimeMessage message = new MimeMessage(emailSession);

		            // Set the sender, recipient, subject, and body of the email
		            message.setFrom(new InternetAddress(username));
		            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(subscriber.getInfo()));
		            
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            message.setSubject("Alert " + LocalDate.now().format(formatter));
		            message.setText(alert.getAlertMessage());

		            // Send email
		            Transport.send(message);

		            System.out.println("Email sent successfully.");
		        } catch (MessagingException e) {
		            System.err.println("Error sending email: " + e.getMessage());
		        }
				break;
			case PHONE:
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				
			    Message message = Message.creator(
			    		new PhoneNumber("+1" + subscriber.getInfo()), 
			    		new PhoneNumber("+12055486482"), 
			    		alert.getAlertMessage())
			    		.create();

			    System.out.println(message.getSid());
				break;
		}
	}
}
