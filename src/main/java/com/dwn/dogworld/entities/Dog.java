package com.dwn.dogworld.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long id;
	
	@Validate("required")
	public String name;
	
	@Validate("required")
	public Date dateOfBirth;
	
	public String birthLocation;
	
	@Validate("required")
	public Long ownerId;
	
	@Validate("required")
	public String gender;
	
	@Validate("required")
	public String colour;
	
	@Validate("required")
	public String breedId;
	
	public int height;
	
	public String description;
	
	public String tagNumber;
	
	private String status;
}
