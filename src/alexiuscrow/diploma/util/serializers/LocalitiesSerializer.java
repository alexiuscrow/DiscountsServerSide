package alexiuscrow.diploma.util.serializers;

import java.io.IOException;

import alexiuscrow.diploma.entity.Localities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalitiesSerializer extends JsonSerializer<Localities> {
	@Override
	public void serialize(Localities locality, JsonGenerator jsonGen,
			SerializerProvider serProv) throws IOException,
			JsonProcessingException {
		jsonGen.writeStartObject();
		jsonGen.writeNumberField("id", locality.getId());
		jsonGen.writeStringField("name", locality.getName());
		jsonGen.writeEndObject();
		
	}
}
