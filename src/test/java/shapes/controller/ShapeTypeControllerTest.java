package shapes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import shapes.models.dto.shapetype.CreateShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/create-shapetype.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/delete-shapetype.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShapeTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShapeTypeController shapeTypeController;

    @Test
    public void controllerIsNotNull() {
        assertNotNull(shapeTypeController);
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapeType/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("circle"));
        assertTrue(body.contains("rectangle"));
        assertTrue(body.contains("triangle"));
    }

    @Test
    public void getByIDTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapeType/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("circle"));
    }

    @Test
    public void failCreateShapeTypeTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO();
        String json = objectMapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapeType")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(post)
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("errorMessages"));
        assertTrue(body.contains("timestamp"));
    }

    @Test
    public void successfulCreateShapeTypeTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO("test", "test", 5);
        String json = objectMapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapeType")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void failUpdateShapeTypeTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO();
        String json = objectMapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapeType")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(put)
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("errorMessages"));
        assertTrue(body.contains("timestamp"));
    }

    @Test
    public void failUpdateShapeTypeNotFoundTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(0L, "test", "test", 7);
        String json = objectMapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapeType")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(put)
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("\"message\":\"Тип фигуры с id = 0 не найден\""));
    }


    @Test
    public void successfulUpdateShapeTypeTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(3L, "test", "test", 6);
        String json = objectMapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapeType")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(put)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void successfulDeleteByIdTest() throws Exception {
        mockMvc.perform(delete("/shapeType/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void failDeleteByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/shapeType/0"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        assertTrue(body.contains("\"message\":\"Тип фигуры с id = 0 не найден\""));
    }
}