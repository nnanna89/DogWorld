package com.dwn.dogworld.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
public class AuditTrail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long id;
	
	public String action;
	public String crud_operation;
	public String username;
	public Timestamp timeOfOperation;
}
