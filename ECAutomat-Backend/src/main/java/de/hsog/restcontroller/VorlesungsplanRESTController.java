package de.hsog.restcontroller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/REST")
public class VorlesungsplanRESTController {

	@GetMapping(value = "/vorlesung", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> vorlesungsJSON() {
		try {
			File file = ResourceUtils.getFile("classpath:static/vorlesungsplan.json");
			ObjectMapper mapper = new ObjectMapper();
			Object json = mapper.readValue(file, Object.class);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json), HttpStatus.OK);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("{\"error\": \"File not found\"}", HttpStatus.NOT_FOUND);
	}
}
