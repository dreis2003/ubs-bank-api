package br.com.ubsbank.infra.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.ubsbank.domain.entity.Estoque;

@Component
public class JsonReader {

	public List<Estoque> read(String fileName) throws IOException {
		
		ClassLoader classLoader = JsonReader.class.getClassLoader();
		
		InputStream is = classLoader.getResourceAsStream(fileName);
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {
			List<Estoque> estoqueLista = new ArrayList<Estoque>();
			
			if (jsonParser.nextToken() != JsonToken.START_OBJECT) {
				throw new IllegalStateException("Arquivo inválido ou sem conteúdo");
			}

			while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

				if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
					Estoque estoque = mapper.readValue(jsonParser, Estoque.class);
					estoqueLista.add(estoque);
				}

			}
			return estoqueLista.stream().distinct().collect(Collectors.toList());

		} catch (Exception ex) {
			return null;
		}

	}

}
