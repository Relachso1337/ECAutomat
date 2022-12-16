package de.hsog.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/REST")
public class VorlesungsplanController {

	@GetMapping(value = "/vorlesung", produces = MediaType.APPLICATION_JSON_VALUE)
	public String vorlesungsJSON() {
		try {
			File file = ResourceUtils.getFile("classpath:static/vorlesungsplan.json");
			ObjectMapper mapper = new ObjectMapper();
			Object object = mapper.readValue(file, Object.class);
			return mapper.writeValueAsString(object);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "yikes";
	}
}
