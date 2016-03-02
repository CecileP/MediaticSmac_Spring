package fr.iocean.application.emprunt;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.service.AdherentService;
import fr.iocean.application.emprunt.model.Emprunt;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.Media.TypeMedia;
import fr.iocean.application.media.service.MediaService;

import static fr.iocean.application.persistence.ConvertDate.*;

@Sql("classpath:db/test-emprunt-data.sql")
public class EmpruntIT extends IntegrationTest {

	@Autowired
	public MediaService mediaService;
	@Autowired
	public AdherentService adherentService;
	
	@Test
	public void test() {
		Media m = new Media("test", "test", TypeMedia.CD);
		jsonHelper.serialize(m);
	}
	
	@Test
	public void testFindAll() throws Exception {
		this.mockMvc
				.perform(get("/api/emprunts"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", equalTo(20)))
				.andExpect(jsonPath("$[1].id", equalTo(21)));
	}

	@Test
	public void testRechercheParMediaExistant() throws Exception {
		this.mockMvc
				.perform(get("/api/emprunts/media/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", equalTo(20)));

		this.mockMvc
				.perform(get("/api/emprunts/media/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", equalTo(21)));
	}

	@Test
	public void testRechercheParMediaInexistant() throws Exception {
		this.mockMvc
				.perform(get("/api/emprunts/media/100"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testRechercheParAdherentExistant() throws Exception {
		this.mockMvc
				.perform(get("/api/emprunts/adherent/10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", equalTo(20)))
				.andExpect(jsonPath("$[1].id", equalTo(21)));
		;
	}

	@Test
	public void testRechercheParAdherentInexistant() throws Exception {
		this.mockMvc
				.perform(get("/api/emprunts/media/100"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testAjoutEmprunt() throws Exception {

		Media m=  mediaService.findById(1L);
		Adherent a = adherentService.findById(10L);

		LocalDate dateEmprunt = LocalDate.of(2016,03,02);
		Emprunt e = new Emprunt(a, m, localDateToDate(dateEmprunt));

		System.out.println(jsonHelper.serialize(e));
		this.mockMvc
				.perform(
						post("/api/emprunts")
								.contentType(MediaType.APPLICATION_JSON)
								.characterEncoding("UTF-8")
								.content(jsonHelper.serialize(e)
						)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isCreated());

		this.mockMvc
				.perform(get("/api/emprunts"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(jsonPath("$",hasSize(3)))
				.andExpect(status().isOk());
	}
}
