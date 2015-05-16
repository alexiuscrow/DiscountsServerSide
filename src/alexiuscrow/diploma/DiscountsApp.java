package alexiuscrow.diploma;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import alexiuscrow.diploma.endpoints.DiscountsResources;
import alexiuscrow.diploma.endpoints.ShopsResources;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api/v1")
public class DiscountsApp extends Application{
	
	public DiscountsApp() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/app/api/v1");
        beanConfig.setResourcePackage("alexiuscrow.diploma.endpoints");
        beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(ShopsResources.class);
//		resources.add(CategoriesResources.class);
		resources.add(DiscountsResources.class);
//		resources.add(LocalitiesResources.class);
//		resources.add(ImagesResources.class);
		
		resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
	}
}


/*
GET___________________________________________________________________________________
Nearest shops:					GET /shops?radius=200&lat=12.14&lng=15.67
Discounts in a nearest shops:	GET /shops/discounts?radius=200&lat=12.14&lng=15.67
All shops in a city:			GET /shops?lat=12.14&lng=15.67
All discounts in a city:		GET /shops/discounts?lat=12.14&lng=15.67
Categories:						GET /categories
Images:							GET	/images?id=1

POST__________________________________________________________________________________
Feedback:						POST /feedbacks
New discount:					POST /discounts
New shop:						POST /shops
*/