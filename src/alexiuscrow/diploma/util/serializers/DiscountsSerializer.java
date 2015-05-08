package alexiuscrow.diploma.util.serializers;

import java.io.IOException;

import alexiuscrow.diploma.entity.Discounts;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DiscountsSerializer extends JsonSerializer<Discounts> {
	@Override
	public void serialize(Discounts discount, JsonGenerator jsonGen,
			SerializerProvider serProv) throws IOException,
			JsonProcessingException {
		
		jsonGen.writeStartObject();
		jsonGen.writeNumberField("id", discount.getId());
		jsonGen.writeStringField("title", discount.getTitle());
		jsonGen.writeStringField("start_date", discount.getStartDate().toString());
		jsonGen.writeStringField("end_date", discount.getEndDate().toString());
		jsonGen.writeStringField("description", discount.getDescription());
		jsonGen.writeStringField("image", discount.getImageUrl());
		jsonGen.writeNumberField("shop_id", discount.getShop().getId());
		jsonGen.writeEndObject();
		
	}
}
