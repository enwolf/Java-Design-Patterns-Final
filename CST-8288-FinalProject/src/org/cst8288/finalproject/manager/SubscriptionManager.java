package org.cst8288.finalproject.manager;

import java.nio.channels.Channel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Properties;
import java.util.Hashtable;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.cst8288.finalproject.subscriptions.Alert;
import org.cst8288.finalproject.subscriptions.Subscriber;
import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.enums.ContactMethod;

public class SubscriptionManager extends SubscriberDAO{
	private SubscriberDAO subscriberDAO;
	private List<Subscriber> subscribers;
	private Alert alertMessage;
	
	private Subscriber subscriber;
	
	// observer pattern requirements 
	private List<Observer> observers = new ArrayList<>();
	private int state;
	
	//Email config for javamail
    private String host = "smtp.gmail.com";
    private int port = 587;
    private String username = "algonquinfwrp@example.com";
    private String password = "AlgonquinDP@";
    private String recipent;
    
    private String subject;
    private String body;
			
	public SubscriptionManager(SubscriberDAO subscriberDAO) {
		
	}
	
	public void subscribe(Subscriber subscriber) {
		createSubscriber(subscriber);
	}
	
	public void unsubscribe(int subscriberID) {
        deleteSubscriber(subscriberID);
	}
	
	public List<Subscriber> listSubscribers(){
		return listSubscribers();
	}

	public void updateSubsContactInfo(int subID, ContactMethod contactMethod, String contactInfo) {
		updateSubscriber(subID, contactMethod, contactInfo);
	}
	
	public Subscriber getSubDetails(){
		return subscriber;
	}
	
	public void notifySub(Alert alert) {
		
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
		            message.addRecipient(Message.RecipientType.TO, new InternetAddress(subscriber.getInfo()));
		            
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
				System.out.println("not implemented/still looking for a method to send alerts through phone (probably Twilio)");
				break;
		}
	}
	
	// Observer Pattern getters and setters
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		//notifyObservers();
	}
	// Observer add/remove methods as well as notify
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	/*public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}*/
}
