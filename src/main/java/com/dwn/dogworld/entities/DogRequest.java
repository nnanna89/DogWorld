package com.dwn.dogworld.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dogRequest")
public class DogRequest {
	
	private Long id;
	
	private String preferredBreed;
	private String preferredGender;
	private String preferredColour;
	private int quantityNeeded;
	private String requestDescription;
	private String contactEmail;
	private String contactPhone;
	private String requestStatus;
	private String requestLocation;
	
	public DogRequest(){
		
	}
	
	public DogRequest(final String breed, final String gender, final String colour,
			final int quantity, final String description, final String email, final String phone,
			final String status, final String location){
		this.preferredBreed = breed;
		this.preferredGender = gender;
		this.preferredColour = colour;
		this.quantityNeeded = quantity;
		this.requestDescription = description;
		this.contactEmail = email;
		this.contactPhone = phone;
		this.requestStatus = status;
		this.requestLocation = location;
	}
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(unique = true)
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
	 * @return the preferredBreed
	 */
	public String getPreferredBreed() {
		return preferredBreed;
	}
	/**
	 * @param preferredBreed the preferredBreed to set
	 */
	public void setPreferredBreed(String preferredBreed) {
		this.preferredBreed = preferredBreed;
	}
	/**
	 * @return the preferredGender
	 */
	public String getPreferredGender() {
		return preferredGender;
	}
	/**
	 * @param preferredGender the preferredGender to set
	 */
	public void setPreferredGender(String preferredGender) {
		this.preferredGender = preferredGender;
	}
	/**
	 * @return the preferredColour
	 */
	public String getPreferredColour() {
		return preferredColour;
	}
	/**
	 * @param preferredColour the preferredColour to set
	 */
	public void setPreferredColour(String preferredColour) {
		this.preferredColour = preferredColour;
	}
	/**
	 * @return the requestDescription
	 */
	public String getRequestDescription() {
		return requestDescription;
	}
	/**
	 * @param requestDescription the requestDescription to set
	 */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
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
	 * @return the requestStatus
	 */
	public String getRequestStatus() {
		return requestStatus;
	}
	/**
	 * @param requestStatus the requestStatus to set
	 */
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	/**
	 * @return the requestLocation
	 */
	public String getRequestLocation() {
		return requestLocation;
	}
	/**
	 * @param requestLocation the requestLocation to set
	 */
	public void setRequestLocation(String requestLocation) {
		this.requestLocation = requestLocation;
	}

	/**
	 * @return the quantityNeeded
	 */
	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	/**
	 * @param quantityNeeded the quantityNeeded to set
	 */
	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}
	
	@Override
	public String toString(){
		return "DogRequest(" + preferredBreed + "," + preferredGender + "," + preferredColour 
				+ "," + requestLocation + "," + contactEmail + ")";
	}
}
