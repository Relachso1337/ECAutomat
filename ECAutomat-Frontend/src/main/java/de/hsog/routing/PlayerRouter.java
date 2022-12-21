package de.hsog.routing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsog.dto.Player;
import de.hsog.errors.BackendServerEntityNotFoundException;
import de.hsog.errors.BackendServerInternalErrorException;
import de.hsog.restrepos.PlayerRESTRepo;

@Controller("/")
public class PlayerRouter {
	
	private PlayerRESTRepo playerRepo;
	
	public PlayerRouter() {
		/*TODO: change address*/
		this.playerRepo = new PlayerRESTRepo();
	}
	
	
	@GetMapping("/Players")
	public String getForm(Model model) {
		model.addAttribute("category", new Player());
		return "html/playerWizard";
	}
	
	@PostMapping("/Players")
	public String submitForm(@ModelAttribute Player player) {
		try {
			this.playerRepo.addPlayer(player.getPlayerName(), player.getScoreQuizes());
		} catch (BackendServerEntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BackendServerInternalErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/Players";
	}

}
