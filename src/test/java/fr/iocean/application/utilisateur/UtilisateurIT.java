package fr.iocean.application.utilisateur;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.utilisateur.model.Utilisateur;


public class UtilisateurIT extends IntegrationTest {
	
	
	@Test
	public void testCreate() {
		Utilisateur u = new Utilisateur();
		u.getPersonne().setNom("pelp");
		u.getPersonne().setPrenom("pino");
		
		

	
		
		
	}
}
