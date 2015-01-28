package com.dwn.dogworld.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class DogOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long id;
	
	public String firstName;
	public String lastName;
	public String kennelName;
	public String contactAddress;
	public String emailAddress;
	public String mobileNumber;
	public String website;
	public Date dateOfCreation;
}
