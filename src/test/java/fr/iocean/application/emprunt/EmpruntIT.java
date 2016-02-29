package fr.iocean.application.emprunt;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import fr.iocean.application.IntegrationTest;

@Sql("classpath:db/test-emprunt-data.sql")
public class EmpruntIT extends IntegrationTest {

	@Test
	public void testFindAll() throws Exception {
		this.mockMvc.perform(
				get("/api/emprunts")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id", equalTo(20)))
		.andExpect(jsonPath("$[1].id", equalTo(21)));
	}

	@Test
	public void testRechercheParMediaExistant() throws Exception {
		this.mockMvc.perform(
				get("/api/emprunts/media/1")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", equalTo(20)));

		this.mockMvc.perform(
				get("/api/emprunts/media/2")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", equalTo(21)));
	}

	@Test
	public void testRechercheParMediaInexistant() throws Exception {
		this.mockMvc.perform(
				get("/api/emprunts/media/100")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testRechercheParAdherentExistant() throws Exception {
		this.mockMvc.perform(
				get("/api/emprunts/adherent/10")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id", equalTo(20)))
		.andExpect(jsonPath("$[1].id", equalTo(21)));;
	}

	@Test
	public void testRechercheParAdherentInexistant() throws Exception {
		this.mockMvc.perform(
				get("/api/emprunts/media/100")
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(0)));
	}
}
