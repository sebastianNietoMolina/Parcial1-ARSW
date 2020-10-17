package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import javafx.beans.binding.Bindings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(PrimesController.class)
public class PrimesrepoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PrimeService ps;

	@Test
	public void getPrimes() throws Exception {
		String res = "{\"user\":\"SebastianNieto\",\"prime\":\"133\"},{\"user\":\"user2\",\"prime\":\"647\"},{\"user\":\"user3\",\"prime\":\"6317\"},{\"user\":\"Prueba\",\"prime\":\"7\"}";
		//when(ps.getFoundPrimes()).thenReturn(res);
		//String res = "{'user':'Prueba','prime':'7'}";
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(res));
	}


}
