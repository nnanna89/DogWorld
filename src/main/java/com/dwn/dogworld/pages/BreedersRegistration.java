package com.dwn.dogworld.pages;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.exception.ConstraintViolationException;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.entities.Breeder;
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
	
	@Property
	private String breedingStartYear;
	
	private Breeder breeder;
	
	@Inject
	private CrudServiceDAO crudDao;
	
	@Persist
	@Property
	private List<String> selectedBreeds;
	
	@Inject
	private ComponentResources resources;
	
	@CommitAfter
	Object onSuccess(){
		System.out.println("Selected Breeds: " + selectedBreeds);
		String[] selectedBreedsArray = null;
		if(selectedBreeds.size() > 0){
			selectedBreedsArray = new String[selectedBreeds.size()];
			int i = 0;
			for(String breed : selectedBreeds){
				selectedBreedsArray[i] = breed;
				i++;
			}
		}else{
			return null; //don't go ahead
		}
		try{
			breeder = new Breeder(contactName, contactEmail, contactNumber, address, selectedBreedsArray, null, breedingStartYear);
			crudDao.create(breeder);
		}catch(ConstraintViolationException e){
			return null;
		}
		
		System.out.println("------------------Breeder's email address: " + breeder.getEmail());
		
		//send email notification to the breeder and admin
		SendEmail emailNotification = new SendEmail();
		emailNotification.sendBreederRegistrationNotification(breeder);
		emailNotification.sendBreederRegistrationNotificationToAdmin(breeder);
		
		//discard the values persisted in session
		resources.discardPersistentFieldChanges();
		
		return null;
	}
	
	@Property
	private String[] commonBreedLines = {"Bull Dog", "Basenji", "Chow Chow", "German Shepherd (Alsatian)",  "Lhasa Apso", "Mastiff", "Pitbull", "Poodle", "Retriever", "Rottweiler", "Others"};
	
	public ValueEncoder<String> getValueEncoder(){
		return new ValueEncoder<String>(){

			@Override
			public String toClient(String value) {
				// TODO Auto-generated method stub
				return value;
			}

			@Override
			public String toValue(String clientValue) {
				// TODO Auto-generated method stub
				return clientValue;
			}
			
		};
	}
	
}
