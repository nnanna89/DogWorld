package com.dwn.dogworld.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dwn.dogworld.entities.Breeder;
import com.dwn.dogworld.entities.CustomerInquiry;
import com.dwn.dogworld.entities.DogRequest;

public class SendEmail implements EmailNotifications {
	private String username;
	private String password;
	private String emailMessage;
	private String subject;
	private String recipient;
	private String timeOfRequest;
	
	private Session session;
	
	public SendEmail(){
		this.session = prepSession();
	}
	
	public SendEmail(String emailMessage, String subject, String recipient, String timeOfRequest){
		this.emailMessage = emailMessage;
		this.subject = subject;
		this.recipient = recipient;
		this.timeOfRequest = timeOfRequest;
		this.session = prepSession();
	}
	
	/**
	 * sets required smtp properties and does a 
	 * password authentication
	 * @return mail session object
	 */
	public Session prepSession(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		//get username and password from db/elsewhere
		
		return Session.getInstance(props, 
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(Constants.SENDER_EMAIL, Constants.EMAIL_PASSWORD);
			}
		});
	}
	
	public void sendPlainTextEmail(String subject, String messageBody,
			String receipient, boolean withAttachment, File attachment) {
		// TODO Auto-generated method stub
		
	}

	public void sendHTMLEmail(String subject, String messageBody,
			String receipient, boolean withAttachment, File attachment) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Initializes the java mail object which holds the email
	 * addresses of all recipients
	 * @param fromEmail, email address
	 * @param toEmail
	 * @param ccEmail
	 * @param bccEmail
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private Message initializeEmailMessage(String fromEmail, String toEmail, String ccEmail, String bccEmail) throws AddressException, MessagingException{
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(fromEmail)); //change email later - retrieve from db 
														//(load it into db first time app starts)
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
		if(ccEmail != null){
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail));
		}
		if(bccEmail != null){
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccEmail));
		}
		message.setSentDate(new Date());
		return message;
	}

	/**
	 * sends email notification to the customer
	 * acknowledging receipt of the dog request
	 */
	public void sendDogRequestNotification(DogRequest request) {
		if(session == null)
			return;
		try {
			Message message = initializeEmailMessage(Constants.SENDER_EMAIL, request.getContactEmail(), Constants.VET_ADMIN_EMAIL, Constants.BLIND_COPY_EMAIL);
			message.setSubject("Dog Request Received - " + new SimpleDateFormat(Constants.EMAIL_DATE_FORMAT).format(new Date()));
			
			String messageBody = "Hello!<br>";
			messageBody += "<br>We have received your request for a dog. See details below:<br><hr>";
			messageBody += "Breed: <strong>" + request.getPreferredBreed() + "</strong><br>";
			messageBody += "Gender: <strong>" + request.getPreferredGender() + "</strong><br>";
			messageBody += "Color: <strong>" + request.getPreferredColour() + "</strong><br>";
			messageBody += "Contact Email: <strong>" + request.getContactEmail() + "</strong><br>";
			messageBody += "Contact Telephone: <strong>" + request.getContactPhone() + "</strong><br>";
			messageBody += "Further Description: <strong>" + request.getPreferredBreed() + "</strong><br>";
			messageBody += "Location: <strong>" + request.getRequestDescription() + "</strong><br>";
			messageBody += "<hr><br>We are currently searching for the best dog deals for you. Our representative will get in touch with you shortly.<br>";
			messageBody += Constants.EMAIL_SIGNATURE;
			message.setContent(messageBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public void sendCustomerInquiryNotification(CustomerInquiry inquiry) {
		if(session == null)
			return;
		try {
			Message message = initializeEmailMessage(Constants.SENDER_EMAIL, inquiry.getContactEmail(), null, null);
			message.setSubject("Inquiry Received - " + new SimpleDateFormat(Constants.EMAIL_DATE_FORMAT).format(new Date()));
			
			String messageBody = "Hello!<br>";
			messageBody += "<br>Thank you for contacting us.";
			messageBody += "<br><br>We will review your message and a representative will get in touch with you shortly.<br><br>";
			messageBody += Constants.EMAIL_SIGNATURE;
			message.setContent(messageBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendInquiryToAdmin(CustomerInquiry inquiry) {
		if(session == null)
			return;
		try {
			Message message = initializeEmailMessage(Constants.SENDER_EMAIL, Constants.INQUIRY_ADMIN_EMAIL, null, Constants.BLIND_COPY_EMAIL);
			message.setSubject("Customer Inquiry Received - " + new SimpleDateFormat(Constants.EMAIL_DATE_FORMAT).format(new Date()));
			
			String messageBody = "Hello!<br>";
			messageBody += "<br>The following inquiry has been received by a customer:<br><hr>";
			messageBody += "Name: <strong>" + inquiry.getContactName() + "</strong><br>";
			messageBody += "Subject: <strong>" + inquiry.getSubject() + "</strong><br>";
			messageBody += "Email: <strong>" + inquiry.getContactEmail() + "</strong><br>";
			messageBody += "Phone Number: <strong>" + inquiry.getContactPhone() + "</strong><br>";
			messageBody += "Message: <strong>" + inquiry.getMessage() + "</strong><br><hr><br>";
			messageBody += Constants.EMAIL_SIGNATURE;
			message.setContent(messageBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendBreederRegistrationNotification(Breeder breeder) {
		if(session == null)
			return;
		try {
			Message message = initializeEmailMessage(Constants.SENDER_EMAIL, breeder.getEmail(), Constants.VET_ADMIN_EMAIL, Constants.BLIND_COPY_EMAIL);
			message.setSubject("DogWorld Nigeria: Registration Received");
			
			String messageBody = "Hello!<br>";
			messageBody += "<br>Thank you for registering with us. <br><br>We will run a short verification process which usually lasts a few hours (between 9am and 5pm daily). ";
			messageBody += "After that, you will be able to upload and advertise your dogs on our platform.<br><br>";
			messageBody += "Our customer service agent will get in touch with you as soon as we are done with the verification process.<br><br>Thank you";
			messageBody += Constants.EMAIL_SIGNATURE;
			message.setContent(messageBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendBreederRegistrationNotificationToAdmin(Breeder breeder) {
		if(session == null)
			return;
		try {
			Message message = initializeEmailMessage(Constants.SENDER_EMAIL, Constants.VET_ADMIN_EMAIL, null, Constants.BLIND_COPY_EMAIL);
			message.setSubject("New Breeder Registration - " + new SimpleDateFormat(Constants.EMAIL_DATE_FORMAT).format(new Date()));
			
			String messageBody = "Hello!<br>";
			messageBody += "<br>The following breeder has registered in the portal, and is pending verification:<br><hr>";
			messageBody += "Name: <strong>" + breeder.getName() + "</strong><br>";
			messageBody += "Breeding since: <strong>" + breeder.getFirstBreedingYear() + "</strong><br>";
			messageBody += "Email: <strong>" + breeder.getEmail() + "</strong><br>";
			messageBody += "Phone Number: <strong>" + breeder.getTelephone() + "</strong><br>";
			messageBody += "Address: <strong>" + breeder.getAddress() + "</strong><br>";
			messageBody += "Breed(s): <strong>" + breeder.getBreeds() + "</strong><br><hr><br>";
			messageBody += Constants.EMAIL_SIGNATURE;
			message.setContent(messageBody, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
