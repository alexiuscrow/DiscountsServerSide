package alexiuscrow.diploma.util.serializers;

import java.lang.reflect.Type;

import alexiuscrow.diploma.entity.Shops;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class NearestShopsSerializer implements JsonSerializer<Shops>{

	@Override
	public JsonElement serialize(Shops shop, Type arg1,
			JsonSerializationContext context) {
		JsonObject jsonShop = new JsonObject();
        jsonShop.addProperty("id", shop.getId());
        jsonShop.addProperty("name", shop.getName());
        jsonShop.addProperty("category", shop.getCategory().toString());
        jsonShop.addProperty("latitude", shop.getLatitude());
        jsonShop.addProperty("longitude", shop.getLongitude());
        jsonShop.addProperty("address", shop.getAddress());
        jsonShop.addProperty("distance", shop.getDistance());
        
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        		.setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        		.setPrettyPrinting()
        		.create();
        JsonElement jsonDiscounts = new JsonParser().parse(gson.toJson(shop.getDiscounts()));
        jsonShop.add("discounts", jsonDiscounts);
        
		return jsonShop;
	}
}
