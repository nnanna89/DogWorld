package com.dwn.dogworld.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class holds the CustomerInquiry entity which logs
 * all inquiries from the ContactUs page
 * @author Nnanna Madu
 * @since 10th Jan, 2014
 *
 */

@Entity
@Table(name="CustomerInquiry")
public class CustomerInquiry {
	private Long id;
	private String contactName;
	private String subject;
	private String contactEmail;
	private String contactPhone;
	private String message;
	private String timeReceived;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(unique=true)
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the timeReceived
	 */
	public String getTimeReceived() {
		return timeReceived;
	}
	/**
	 * @param timeReceived the timeReceived to set
	 */
	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}
	
	@Override
	public String toString(){
		return "CustomerInquiry(" + contactName + "," + subject + "," + contactEmail 
				+ "," + contactPhone + "," + message + ")";
	}
	
	public CustomerInquiry(final String contactName, final String subject, final String contactEmail, final String contactPhone,
			final String message){
		this.contactName = contactName;
		this.subject = subject;
		this.contactEmail = contactEmail;
		this.contactPhone = contactPhone;
		this.message = message;
	}
}