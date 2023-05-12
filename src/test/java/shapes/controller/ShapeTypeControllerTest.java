package shapes.controller;

import com.fasterxml.jackson.databind.JsonNode;
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
import shapes.models.dto.shapetype.ShapeTypeDTO;
import shapes.models.dto.shapetype.UpdateShapeTypeDTO;

import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/sql/create-shapetype.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete-shapetype.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShapeTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShapeTypeController controller;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void controllerIsNotNull() {
        assertNotNull(controller);
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapeType/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        List<ShapeTypeDTO> shapeTypes = Arrays.asList(mapper.readValue(body, ShapeTypeDTO[].class));
        assertTrue(body.contains("circle"));
        assertTrue(body.contains("rectangle"));
        assertTrue(body.contains("triangle"));
        assertEquals(1L, shapeTypes.get(0).getShapeTypeId());
        assertEquals("circle", shapeTypes.get(0).getSystemName());
        assertEquals(2L, shapeTypes.get(1).getShapeTypeId());
        assertEquals("triangle", shapeTypes.get(1).getSystemName());
        assertEquals(3L, shapeTypes.get(2).getShapeTypeId());
        assertEquals("rectangle", shapeTypes.get(2).getSystemName());
    }

    @Test
    public void getByIDTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapeType/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        ShapeTypeDTO shapeTypeDTO = mapper.readValue(body, ShapeTypeDTO.class);
        assertEquals(1L, shapeTypeDTO.getShapeTypeId());
        assertEquals("circle", shapeTypeDTO.getSystemName());
    }

    @Test
    public void failCreateShapeTypeTest() throws Exception {
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO();
        String json = mapper.writeValueAsString(dto);
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
        CreateShapeTypeDTO dto = new CreateShapeTypeDTO("test", "test", 5);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapeType")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void failUpdateShapeTypeTest() throws Exception {
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO();
        String json = mapper.writeValueAsString(dto);
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
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(0L, "test", "test", 7);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapeType")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(put)
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        JsonNode jsonNode = mapper.readTree(body);
        assertEquals("Тип фигуры с id = 0 не найден", jsonNode.get("message").asText());
    }

    @Test
    public void successfulUpdateShapeTypeTest() throws Exception {
        UpdateShapeTypeDTO dto = new UpdateShapeTypeDTO(3L, "test", "test", 6);
        String json = mapper.writeValueAsString(dto);
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
        JsonNode jsonNode = mapper.readTree(body);
        assertEquals("Тип фигуры с id = 0 не найден", jsonNode.get("message").asText());
    }
}