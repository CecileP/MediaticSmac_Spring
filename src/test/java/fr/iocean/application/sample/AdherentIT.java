package fr.iocean.application.sample;

import static fr.iocean.application.persistence.ConvertDate.localDateToDate;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.adherents.model.Adherent;
import fr.iocean.application.adherents.service.AdherentService;
import fr.iocean.application.exception.NotFoundException;

@Sql("classpath:db/test-adherent-data.sql")
public class AdherentIT extends IntegrationTest{

	@Autowired
	public AdherentService adherentService;
	
	
	@Test
	public void testcreated() throws Exception {
		
		Adherent u = new Adherent("trtr","yyy", localDateToDate(LocalDate.of(1995,06,03)));
		u.setAdresseMail("@mail");
		
		
		this.mockMvc.perform(post("/api/adherent")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u)))
			.andExpect(status().isCreated());

		this.mockMvc.perform(get("/api/adherent"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$",hasSize(3)))
			.andExpect(status().isOk());
	}
		
	
	@Test
	public void testCreateNotValid() throws Exception{
		Adherent u = new Adherent();
		
		this.mockMvc.perform(post("/api/adherent")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(jsonHelper.serialize(u)))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isPreconditionFailed());
	}
	
/*-------------------- fingbyid  -----------------------------*/
	
	@Test
	public void testFindById1() throws Exception{
		this.mockMvc.perform(get("/api/adherent/{id}",1))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.id").value(1))
					.andExpect(jsonPath("$.auteur").value("auteur"))
					.andExpect(jsonPath("$.titre").value("titre1"))
					.andExpect(jsonPath("$.typeMedia").value("DVD"))
					.andExpect(status().isOk());
	}
	@Test
	public void testFindByIdError() throws Exception{
		this.mockMvc.perform(get("/api/adherent/{id}",3))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isNotFound());
	}
	
	/*-------------------- find all ----------------*/
	@Test
	public void testfindAll() throws Exception{
		this.mockMvc.perform(get("/api/adherent"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$",hasSize(2)))
					.andExpect(status().isOk());
	}
	
/* ---------------------  update -------------------*/
	
	@Test
	public void testUpdate() throws Exception{
		Adherent u = adherentService.findById(1l);
		assertEquals("lulu", u.getNom());
		
		u.setNom("nom1");
		
		this.mockMvc.perform(put("/api/adherent/{id}",1)
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(jsonHelper.serialize(u)))
					.andExpect(jsonPath("$.titre").value("tir"))
					.andExpect(status().isOk());
	}
	
/*------------- delete --------------*/
	
	@Test(expected=NotFoundException.class)
	public void testDelete() throws Exception{
		Adherent u = adherentService.findById(1l);
		assertNotNull(u);
		
		this.mockMvc.perform(delete("/api/adherent/{id}",1))
					.andExpect(status().isOk());
		
		Adherent uDelete = adherentService.findById(1l);
		assertNull(uDelete);
	}
}
