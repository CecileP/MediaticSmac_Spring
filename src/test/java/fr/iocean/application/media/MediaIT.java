package fr.iocean.application.media;
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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.exception.NotFoundException;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.Media.TypeMedia;
import fr.iocean.application.media.service.MediaService;



@Sql("classpath:db/test-media-data.sql")
public class MediaIT extends IntegrationTest {

	@Autowired
	public MediaService mediaService;
	
	
	@Test
	public void testcreated() throws Exception {
		Media u = new Media();
		u.setAuteur("autr1");
		u.setTitre("pino");
		u.setTypeMedia(TypeMedia.DVD);
		System.out.println(u);
		System.out.println(jsonHelper.serialize(u));

		this.mockMvc.perform(post("/api/medias")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(jsonHelper.serialize(u)))
			.andExpect(status().isCreated());

		this.mockMvc.perform(get("/api/medias"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$",hasSize(3)))
			.andExpect(status().isOk());
	}

	@Test
	public void testCreateNotValid() throws Exception{
		Media u = new Media();
		
		this.mockMvc.perform(post("/api/medias")
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(jsonHelper.serialize(u)))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isPreconditionFailed());
	}

/*-------------------- fingbyid  -----------------------------*/
	
	@Test
	public void testFindById1() throws Exception{
		this.mockMvc.perform(get("/api/medias/{id}",1))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$.id").value(1))
					.andExpect(jsonPath("$.auteur").value("auteur"))
					.andExpect(jsonPath("$.titre").value("titre1"))
					.andExpect(jsonPath("$.typeMedia").value("DVD"))
					.andExpect(status().isOk());
	}
	@Test
	public void testFindByIdError() throws Exception{
		this.mockMvc.perform(get("/api/medias/{id}",3))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isNotFound());
	}
	
	/*-------------------- find all ----------------*/
	@Test
	public void testfindAll() throws Exception{
		this.mockMvc.perform(get("/api/medias"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(jsonPath("$",hasSize(2)))
					.andExpect(status().isOk());
	}

/* ---------------------  update -------------------*/
	
	@Test
	public void testUpdate() throws Exception{
		Media u = mediaService.findById(1l);
		assertEquals("titre1", u.getTitre());
		
		u.setTitre("tir");
		
		this.mockMvc.perform(put("/api/medias/{id}",1)
						.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
						.content(jsonHelper.serialize(u)))
					.andExpect(jsonPath("$.titre").value("tir"))
					.andExpect(status().isOk());
	}

/*------------- delete --------------*/
	
	@Test(expected=NotFoundException.class)
	public void testDelete() throws Exception{
		Media u = mediaService.findById(1l);
		assertNotNull(u);
		
		this.mockMvc.perform(delete("/api/medias/{id}",1))
					.andExpect(status().isOk());
		
		Media uDelete = mediaService.findById(1l);
		assertNull(uDelete);
	}
	
	/*--------------recherche------------------*/
	
//	@Test(expected=NotFoundException.class)
//	public void testDelete() throws Exception{
//		Media u = mediaService.findById(1l);
//		assertNotNull(u);
//		
//		this.mockMvc.perform(delete("/api/medias/{id}",1))
//					.andExpect(status().isOk());
//		
//		Media uDelete = mediaService.findById(1l);
//		assertNull(uDelete);
//	}
//	rechercheMedia
//	
}