package com.dwn.dogworld.pages;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.exception.ConstraintViolationException;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.data.Breed;
import com.dwn.dogworld.entities.Breeder;
import com.dwn.dogworld.utils.EmailNotifications;
import com.dwn.dogworld.utils.SendEmail;

/**
 * Registration page for breeders
 * @author Nnanna Madu
 * @since 23 Jan, 2014
 */
public class BreedersRegistration
{
	@Component
	private Form registrationForm;
	
	@Property
	private String contactName;
	
	@Property
	private String contactEmail;
	
	@Property
	private String contactNumber;
	
	@Property
	private String address;
	
	private Breeder breeder;
	
	@Inject
	private CrudServiceDAO crudDao;
	
	@Property
	private String selectedBreeds;
	
	@Property
	private SelectModel breeds = new Breed();
	
	@CommitAfter
	Object onSuccess(){
		System.out.println("Selected Breeds: " + selectedBreeds);
		
		try{
			breeder = new Breeder(contactName, contactEmail, contactNumber, address, null, null);
			crudDao.create(breeder);
		}catch(ConstraintViolationException e){
			System.out.println("");
			return null;
		}
//		SendEmail emailNotification = new SendEmail();
//		emailNotification.sendCustomerInquiryNotification(customerInquiry);
//		emailNotification.sendInquiryToAdmin(customerInquiry);
		System.out.println("Thank you for registering with us. We will begin a short verification process which usually lasts 1-3 days.");
		
		return null;
	}
	
}
