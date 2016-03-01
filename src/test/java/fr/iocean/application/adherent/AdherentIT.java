package fr.iocean.application.adherent;

import static fr.iocean.application.persistence.ConvertDate.localDateToDate;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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
import fr.iocean.application.exception.NotFoundException;

@Sql("classpath:db/test-adherent-data.sql")
public class AdherentIT extends IntegrationTest{

	@Autowired
	public AdherentService adherentService;
	
	
	@Test
	public void testcreated() throws Exception {
		
		Adherent u = new Adherent();
		u.getPersonne().setNom("trtr");
		u.getPersonne().setPrenom("yyy");
		u.setDateNaissance(localDateToDate(LocalDate.of(1995,06,03)));
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
	public void testcreated1() throws Exception {
		
		Adherent u = new Adherent("trtr","yyy",localDateToDate(LocalDate.of(1995,06,03)));
		u.setAdresseMail("@mail");
		System.out.println("hhh" + u);
		System.out.println("hhhh" + jsonHelper.serialize(u));
		
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
					.andExpect(jsonPath("$.personne.nom").value("lulu"))
					.andExpect(jsonPath("$.personne.prenom").value("roger"))
					.andExpect(jsonPath("$.adresseMail").value("@gh"))
					.andExpect(jsonPath("$.dateNaissance", equalTo("1992-03-04")))
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
		assertEquals("lulu", u.getPersonne().getNom());
		
		u.getPersonne().setNom("nom1");
		
		System.out.println("trtrt" +jsonHelper.serialize(u));
		
		this.mockMvc.perform(put("/api/adherent/{id}",1)
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(jsonHelper.serialize(u)))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.personne.nom").value("nom1"))
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
