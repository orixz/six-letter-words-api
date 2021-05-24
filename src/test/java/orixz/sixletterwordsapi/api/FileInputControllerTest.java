package orixz.sixletterwordsapi.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileInputControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testSixWordApi() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        byte[] bytes = classLoader.getResourceAsStream("input.txt").readAllBytes();

        mvc.perform(post("/api/file")
                .contentType(MediaType.TEXT_PLAIN)
                .content(bytes))
                .andExpect(status().isOk());

    }
}