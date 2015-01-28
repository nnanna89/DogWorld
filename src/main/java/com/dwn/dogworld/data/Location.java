package com.dwn.dogworld.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.util.AbstractSelectModel;

public class Location extends AbstractSelectModel {
	
	public List<OptionModel> options = new ArrayList<OptionModel>();
//	public List<OptionGroupModel> optionGroups = new ArrayList<OptionGroupModel>();
	
	public Location(){
		options.add(new OptionModelImpl("Abia"));
		options.add(new OptionModelImpl("Abuja (FCT)"));
		options.add(new OptionModelImpl("Adamawa"));
		options.add(new OptionModelImpl("Akwa Ibom"));
		options.add(new OptionModelImpl("Anambra"));
		options.add(new OptionModelImpl("Bauchi"));
		options.add(new OptionModelImpl("Bayelsa"));
		options.add(new OptionModelImpl("Benue"));
		options.add(new OptionModelImpl("Borno"));
		options.add(new OptionModelImpl("Cross River"));
		options.add(new OptionModelImpl("Delta"));
		options.add(new OptionModelImpl("Ebonyi"));
		options.add(new OptionModelImpl("Edo"));
		options.add(new OptionModelImpl("Ekiti"));
		options.add(new OptionModelImpl("Enugu"));
		options.add(new OptionModelImpl("Gombe"));
		options.add(new OptionModelImpl("Imo"));
		options.add(new OptionModelImpl("Jigawa"));
		options.add(new OptionModelImpl("Kaduna"));
		options.add(new OptionModelImpl("Kano"));
		options.add(new OptionModelImpl("Katsina"));
		options.add(new OptionModelImpl("Kebbi"));
		options.add(new OptionModelImpl("Kogi"));
		options.add(new OptionModelImpl("Kwara"));
		options.add(new OptionModelImpl("Lagos"));
		options.add(new OptionModelImpl("Nasarawa"));
		options.add(new OptionModelImpl("Niger"));
		options.add(new OptionModelImpl("Ogun"));
		options.add(new OptionModelImpl("Ondo"));
		options.add(new OptionModelImpl("Osun"));
		options.add(new OptionModelImpl("Oyo"));
		options.add(new OptionModelImpl("Plateau"));
		options.add(new OptionModelImpl("Rivers"));
		options.add(new OptionModelImpl("Sokoto"));
		options.add(new OptionModelImpl("Taraba"));
		options.add(new OptionModelImpl("Yobe"));
		options.add(new OptionModelImpl("Zamfara"));
	}

	public List<OptionGroupModel> getOptionGroups() {
		// TODO Auto-generated method stub
//		return optionGroups;
		return null;
	}

	public List<OptionModel> getOptions() {
		// TODO Auto-generated method stub
		return options;
	}

}
