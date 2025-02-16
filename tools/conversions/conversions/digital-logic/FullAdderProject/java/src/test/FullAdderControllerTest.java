package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FullAdderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddEndpoint() throws Exception {
        mockMvc.perform(post("/api/add")
                .param("A", "true")
                .param("B", "false")
                .param("Cin", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sum: false, Cout: true"));
    }

    @Test
    public void testClearLogEndpoint() throws Exception {
        mockMvc.perform(post("/api/clearLog"))
                .andExpect(status().isOk())
                .andExpect(content().string("Database Cleared!"));
    }
}