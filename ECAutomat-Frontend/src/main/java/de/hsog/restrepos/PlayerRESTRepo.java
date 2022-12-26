//package de.hsog.restrepos;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import de.hsog.dto.Player;
//import de.hsog.errors.BackendServerEntityNotFoundException;
//import de.hsog.errors.BackendServerInternalErrorException;
//import de.hsog.errors.ErrorHandler;
//
//public class PlayerRESTRepo {
//	
//	private final String serverAddress;
//	private final int serverPort;
//	private final String restContext;
//	private final RestTemplate template;
//	private ErrorHandler handler;
//	
//	public PlayerRESTRepo() {
//		this.serverAddress = "http://127.0.0.1";
//		this.serverPort = 8888;
//		this.restContext = "api/v1/REST";
//		this.template = new RestTemplate();
//		this.handler = new ErrorHandler();
//	}
//	
//	public PlayerRESTRepo(String address, int port, String restContext) {
//		this.serverAddress = address;
//		this.serverPort = port;
//		this.restContext = restContext;
//		this.template = new RestTemplate();
//	} 
//	
//	public List<Player> getAllUsers() throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		ResponseEntity<Player[]> response;
//		String apiLink = String.format("%s:%d/%s/Players", this.serverAddress, this.serverPort, this.restContext); 
//		response = this.template.getForEntity(apiLink, Player[].class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return Arrays.asList(response.getBody());
//	}
//	
//	public Player getPlayerById(int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		String apiLink = String.format("%s:%d/%s/Players/%d", this.serverAddress, this.serverPort, this.restContext, id); 
//		ResponseEntity<Player> response = this.template.getForEntity(apiLink, Player.class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return response.getBody();
//	}
//	
//	public Player addPlayer(String playerName, int scoreQuizes) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		Player player = new Player(playerName, scoreQuizes, new ArrayList<>());
//		HttpEntity<Player> request = new HttpEntity<Player>(player);
//		String apiLink = String.format("%s:%d/%s/Players", this.serverAddress, this.serverPort, this.restContext);
//		ResponseEntity<Player> response = this.template.postForEntity(apiLink, request, Player.class);
//		this.handler.checkForHTMLError(response.getStatusCode(), response.getBody().toString());
//		return response.getBody();
//	}
//	
//	public String updatePlayer(String newPlayerName, int newPlayerScore, int id) throws BackendServerEntityNotFoundException, BackendServerInternalErrorException {
//		Player player = this.getPlayerById(id);
//		player.setPlayerName(newPlayerName);
//		player.setScoreQuizes(newPlayerScore);
//		HttpEntity<Player> request = new HttpEntity<Player>(player);
//		String apiLink = String.format("%s:%d/%s/Players/%d", this.serverAddress, this.serverPort, this.restContext, id);
//		this.template.put(apiLink, request, Player.class);
//		return apiLink;
//	}
//	
//	public String deletePlayer(int id) {
//		String apiLink = String.format("%s:%d/%s/Players/%d", this.serverAddress, this.serverPort, this.restContext, id);
//		this.template.delete(apiLink);
//		return apiLink;
//	}
//}
