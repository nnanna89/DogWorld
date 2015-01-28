package com.dwn.dogworld.utils;

import java.io.File;

import com.dwn.dogworld.entities.CustomerInquiry;
import com.dwn.dogworld.entities.DogRequest;

/**
 * Abstract methods for defining the external behaviours of 
 * email notifications
 * 
 * @author Nnanna Madu
 * @since 4th Jan, 2015
 *
 */
public interface EmailNotifications {
	
	/**
	 * Sends a plain text email
	 * @param subject
	 * @param messageBody
	 * @param receipient
	 * @param withAttachment
	 * @param attachment
	 */
	public void sendPlainTextEmail(String subject, String messageBody, String receipient, boolean withAttachment, File attachment);
	
	/**
	 * Sends a HTML (formatted) email
	 * @param subject
	 * @param messageBody
	 * @param receipient
	 * @param withAttachment
	 * @param attachment
	 */
	public void sendHTMLEmail(String subject, String messageBody, String receipient, boolean withAttachment, File attachment);
	
	/**
	 * Sends a dog request notification to the customer,
	 * breeders, and dogworld admin
	 * @param request
	 */
	public void sendDogRequestNotification(DogRequest request);
	/**
	 * sends notifications after the form in the contact us page
	 * has been filled
	 * @param inquiry
	 */
	public void sendCustomerInquiryNotification(CustomerInquiry inquiry);
}
