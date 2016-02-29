package fr.iocean.application.sample;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import fr.iocean.application.IntegrationTest;
import fr.iocean.application.media.model.Media;
import fr.iocean.application.media.model.Media.TypeMedia;


@Sql("classpath:test-media-data.sql")
public class MediaIT extends IntegrationTest {

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
	
	
	
}