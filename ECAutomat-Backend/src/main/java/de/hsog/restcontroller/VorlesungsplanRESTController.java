package de.hsog.restcontroller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value = "/REST")
public class VorlesungsplanRESTController {
	
	private final ObjectMapper mapper;
	
	public VorlesungsplanRESTController() {
		this.mapper = new ObjectMapper();
	}

	
	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin", origins = "*")
	@GetMapping(value = "/vorlesung", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> vorlesungsJSON() {
		// TODO: Added Wildcard to Access-Control-Allow-Origin CORS-Header, could be dangerous
		ClassPathResource cpr = new ClassPathResource("static/vorlesungsplan.json");
		String responseBody;
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
			String data = new String(bdata, StandardCharsets.UTF_8);
			// File file = ResourceUtils.getFile("classpath:static/vorlesungsplan.json");  // cant be used in jar
			Object json = mapper.readValue(data, Object.class);
			return new ResponseEntity<String>(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json), HttpStatus.OK);
		} catch (FileNotFoundException e) {
			responseBody = "Could not find File on Server";
		} catch (Exception e) {
			responseBody = e.toString();
		}
		try {
			responseBody = this.mapper.writeValueAsString(responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(responseBody, HttpStatus.NOT_FOUND);
	}
}
