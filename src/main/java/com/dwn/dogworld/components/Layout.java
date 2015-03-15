package com.dwn.dogworld.components;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.BindingConstants;

/**
 * Layout component for dogworld pages
 */
@Import(stylesheet = {
		"context:ufd_assets/css/font-awesome.css", 
		"context:ufd_assets/css/bootstrap.min.css",
		"context:ufd_assets/css/mvpready-admin.css",
		"context:ufd_assets/css/mvpready-flat.css",
		"context:ufd_assets/css/ionicons.css",
		"context:ufd_assets/css/flexslider.css",
		"context:ufd_assets/css/home.css",
		"context:ufd_assets/css/animate.css",
		"context:ufd_assets/fonts/open-sans/open-sans.css"
		},
		library = {
		"context:ufd_assets/js/jquery.min.js", 
		"context:ufd_assets/js/jquery.flexslider.min.js", 
		"context:ufd_assets/js/theme.js",
		"context:ufd_assets/js/bootstrap.min.js",
})

public class Layout
{
    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    //Page switching codes
    Object onIndex(){
    	return "Index";
    }
    
    Object onDogRequest(){
    	return "DogRequests";
    }
    
    Object onAvailableDogs(){
    	return "AvailableDogs";
    }
    
    Object onContactUs(){
    	return "ContactUs";
    }
    
    Object onAddDog(){
    	return "AddDog";
    }
    
    Object onBreedersReg(){
    	return "BreedersRegistration";
    }
    
    Object onBreedInformation(){
    	return "BreedInformation";
    }
}
