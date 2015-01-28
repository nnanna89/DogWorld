package com.dwn.dogworld.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.entities.CustomerInquiry;
import com.dwn.dogworld.utils.EmailNotifications;
import com.dwn.dogworld.utils.SendEmail;

/**
 * Contact us page
 * @author Nnanna Madu
 * @since 13 Oct, 2014
 */
public class ContactUs
{
	@Component
	private Form contactForm;
	
	@Property
	private String contactName;
	
	@Property
	private String subject;
	
	@Property
	private String contactEmail;
	
	@Property
	private String contactNumber;
	
	@Property
	private String message;
	
	private CustomerInquiry customerInquiry;
	
	@Inject
	private CrudServiceDAO crudDao;
	
	@CommitAfter
	Object onSuccess(){
		System.out.println("--------------------Successful!!!--------------------");
		customerInquiry = new CustomerInquiry(contactName, subject, contactEmail, contactNumber, message);
		crudDao.create(customerInquiry);
		
		SendEmail emailNotification = new SendEmail();
		emailNotification.sendCustomerInquiryNotification(customerInquiry);
		emailNotification.sendInquiryToAdmin(customerInquiry);
		
		return null;
	}
	
}
