package com.dwn.dogworld.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.dwn.dogworld.dal.CrudServiceDAO;
import com.dwn.dogworld.dal.QueryParameters;
import com.dwn.dogworld.entities.DogBreed;

public class BreedInformation {
	@Component
	private Form searchForm;
	
	@Property
	@Persist
	private String searchParameter;
	
	@Inject
	private CrudServiceDAO crudDao;
	
	@Property
	int indexProp;
	
	@Property
	DogBreed dogBreed;
	
	@Property
	List<DogBreed> breedList;
	
	@Inject
	AlertManager alertManager;
	
	Logger logger = Logger.getLogger(BreedInformation.class);

	@BeforeRenderBody
	void setupRender(){
		logger.info("-----------------Inside Setup Render----------------");
		//if no breeds exists in db, populate breeds from text file BREED_NAME, IMAGE_URL
		List<DogBreed> allBreeds = crudDao.findWithNamedQuery(DogBreed.ALL_BREEDS);
		if(allBreeds == null || allBreeds.isEmpty()){
//			read breeds from text file
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(System.getProperty("user.home") + File.separator + "breeds.txt")); //find a reasonable place to put this
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				logger.error("Breeds file not found:", e);
				return;
			}
			try {
				if(reader.ready()){
					String lineEntry;
					while((lineEntry = reader.readLine()) != null){
//						logger.info("lineEntry: " + lineEntry);
						DogBreed breed = crudDao.findUniqueWithNamedQuery(DogBreed.BY_NAME, 
								QueryParameters.with("name", lineEntry).parameters());
						if(breed != null){
							breed = new DogBreed();
							breed.setName(lineEntry);
							breed.setDescription(lineEntry);
							breed.setImageUrl(lineEntry);
							
							crudDao.create(breed);
						}
					}
				}else{
					logger.info("Reader is null");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			logger.info("There are breeds available in the database!!");
		}
	}
	
	@CommitAfter
	Object onSuccess(){
		logger.info("-------------searchParameter: " + searchParameter);
		breedList = crudDao.findWithNamedQuery(DogBreed.BY_NAME, 
				QueryParameters.with("name", "%" + searchParameter + "%").parameters());
		if(breedList == null){
			logger.info("---------No breed found in database---------");
			//save the search name for future analysis
			
		}else{
			logger.info(breedList.size() + " results found");
		}
		return null;
	}
	
	Object onViewBreedInfo(Long breedId){
		//retrieve the selected breed by id and redirect to appropriate page
		
		return null;
	}
}
