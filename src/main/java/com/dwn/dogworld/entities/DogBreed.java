package com.dwn.dogworld.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = DogBreed.BY_NAME, query="select b from DogBreed b where lower(b.name) like lower(:name)"),
	@NamedQuery(name = DogBreed.ALL_BREEDS, query="select b from DogBreed b")
})
@Table(name = "dogBreed")
public class DogBreed implements Serializable {
	private static final Long serialVersionUID = -9200804524025548138L;
	public static final String BY_NAME = "DogBreed.BY_NAME";
	public static final String ALL_BREEDS = "DogBreed.ALL_BREEDS";

	private Long id;
	private String name;
	private String description;
	private String imageUrl;

	public DogBreed(){

	}

	public DogBreed(final String name, final String description){
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue
	public Long getId(){
		return id;
	}
	
	public void setId(final Long id){
		this.id = id;
	}
	
	@Column(name="name", unique = true, nullable = false, length = 50)
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

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString(){
		return "DogBreed(" + name + "," + description + ")";
	}
}
