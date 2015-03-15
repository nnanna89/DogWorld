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
	@NamedQuery(name = Breeder.BY_BREEDS, query="select b from Breeder b where lower(b.breeds) like lower(:breed)"),
	@NamedQuery(name = Breeder.BY_NAME, query="select b from Breeder b where lower(b.name) = lower(:breederName)"),
	@NamedQuery(name = Breeder.BY_EMAIL, query="select b from Breeder b where lower(b.email) = lower(:breederEmail)"),
	@NamedQuery(name = Breeder.BY_TELEPHONE, query="select b from Breeder b where lower(b.name) = lower(:breederTelephone)")
})
@Table(name="breeder")
public class Breeder{
//	private static final long serialVersionUID = -6176295317720795275L;
	public static final String BY_BREEDS = "Breeder.byBreeds";
	public static final String BY_NAME = "Breeder.byName";
	public static final String BY_EMAIL = "Breeder.byEmail";
	public static final String BY_TELEPHONE = "Breeder.byTelephone";
	
	private Long id;
	
	private String name;
	private String address;
	private String email;
	private String telephone;
	private String firstBreedingYear;
	private String breeds;
	private byte[] logo;
	
	public Breeder(){
		
	}
	
	public Breeder(final String name, final String address, final String email, final String telephone,
			final String breeds, final byte[] logo, final String firstBreedingYear){
		this.name = name;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
		this.breeds = breeds;
		this.logo = logo;
		this.firstBreedingYear = firstBreedingYear;
	}
	
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
	 * @return the name
	 */
	@Column(unique=true)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the email
	 */
	@Column(unique=true)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the telephone
	 */
	@Column(unique=true)
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the breeds
	 */
	public String getBreeds() {
		return breeds;
	}
	/**
	 * @param breeds the breeds to set
	 */
	public void setBreeds(String breeds) {
		this.breeds = breeds;
	}
	/**
	 * @return the logo
	 */
	public byte[] getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	
	/**
	 * @return the firstBreedingYear
	 */
	public String getFirstBreedingYear() {
		return firstBreedingYear;
	}

	/**
	 * @param firstBreedingYear the firstBreedingYear to set
	 */
	public void setFirstBreedingYear(String firstBreedingYear) {
		this.firstBreedingYear = firstBreedingYear;
	}

	@Override
	public String toString(){
		return "Breeder(" + name + "," + address + "," + email + "," + telephone + ","
				+ breeds + ")";
	}
}
