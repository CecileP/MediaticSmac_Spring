package fr.iocean.application.emprunt;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.persistence.ConvertDate;

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

	@Test
	public void testAjoutEmprunt() throws Exception {
		Date naissance = ConvertDate.localDateToDate(LocalDate.of(2000, 1, 2));
		Adherent a = new Adherent("Dupont", "Paul", naissance);
		a.setId(10l);
		Media m = new Media("Les Misérables", "Victor Hugo", Media.TypeMedia.Livre);
		m.setId(1l);

		LocalDate dateEmprunt = LocalDate.of(2016,  3, 2);
		Emprunt e = new Emprunt(a, m, ConvertDate.localDateToDate(dateEmprunt));

		System.out.println(jsonHelper.serialize(e));
		this.mockMvc.perform(
				post("/api/emprunts")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(jsonHelper.serialize(e))
		)
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.media.id", equalTo(1)))
		.andExpect(jsonPath("$.adherent.id", equalTo(10)))
		.andExpect(jsonPath("$.dateEmprunt", equalTo(1456873200000l)));
	}
}
