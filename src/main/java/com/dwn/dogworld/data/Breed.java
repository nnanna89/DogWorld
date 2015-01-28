package com.dwn.dogworld.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

public class Breed extends AbstractSelectModel {
	
	public List<OptionModel> options = new ArrayList<OptionModel>();
	
	public Breed(){
		options.add(new OptionModelImpl("Akita"));
		
		options.add(new OptionModelImpl("Basenji"));
		options.add(new OptionModelImpl("Boer Boel"));
		options.add(new OptionModelImpl("Bulldog"));
		options.add(new OptionModelImpl("Bullmastiff"));

		options.add(new OptionModelImpl("Chow Chow"));
		options.add(new OptionModelImpl("Chihuahua"));
		
		options.add(new OptionModelImpl("Dalmatian"));
		options.add(new OptionModelImpl("Doberman Pinscher"));
		
		options.add(new OptionModelImpl("German Shepherd"));
		options.add(new OptionModelImpl("Golden Retriever"));
		
		options.add(new OptionModelImpl("Labrador Retriever"));
		options.add(new OptionModelImpl("Lhasa Apso"));
		
		options.add(new OptionModelImpl("Mastiff (Old English)"));
		
		options.add(new OptionModelImpl("Neapolitan Mastiff"));
		
		options.add(new OptionModelImpl("Pitbull"));
		options.add(new OptionModelImpl("Poodle"));

		options.add(new OptionModelImpl("Rottweiler"));
		
		options.add(new OptionModelImpl("Saint Bernard"));
		options.add(new OptionModelImpl("Samoyed"));
	}

	public List<OptionGroupModel> getOptionGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OptionModel> getOptions() {
		// TODO Auto-generated method stub
		return options;
	}

}
