package com.dwn.dogworld.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "dogBreed")
public class DogBreed implements Serializable {
	private static final Long serialVersionUID = -9200804524025548138L;

	public Long id;

	@NaturalId
	@Column(unique = true, nullable = false, length = 50)
	private String name;
	private String description;

	public DogBreed(){

	}

	public DogBreed(final String name, final String description){
		this.name = name;
		this.setDescription(description);
	}
	
	@Id
	@GeneratedValue
	public Long getId(){
		return id;
	}
	
	public void setId(final Long id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(final String name){
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		return "DogBreed(" + name + "," + description + ",";
	}
}
