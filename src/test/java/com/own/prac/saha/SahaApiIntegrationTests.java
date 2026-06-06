package com.own.prac.saha;

import com.own.prac.saha.repository.ImgRepository;
import com.own.prac.saha.repository.PropertyRepository;
import com.own.prac.saha.repository.TextRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SahaApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ImgRepository imgRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TextRepository textRepository;

    @Before
    public void cleanDatabase() {
        imgRepository.deleteAll();
        propertyRepository.deleteAll();
        textRepository.deleteAll();
    }

    @Test
    public void getPropertiesReturnsEmptyListWhenNoContentExists() throws Exception {
        mockMvc.perform(get("/property"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void postTextPersistsTextContent() throws Exception {
        mockMvc.perform(post("/text")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"paragraph\":\"Welcome to the saw complex.\",\"type\":\"intro\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/text/")));

        mockMvc.perform(get("/text"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].paragraph").value("Welcome to the saw complex."))
                .andExpect(jsonPath("$[0].type").value("intro"));
    }

    @Test
    public void getPropertyByPropertyIdReturnsLinkedImages() throws Exception {
        mockMvc.perform(post("/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propertyId\":\"main-saw\",\"paragraph\":\"Main saw building.\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/property/")));

        mockMvc.perform(post("/image")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Front view\",\"sahaPhotoURL\":\"https://photos.example/main-saw.jpg\",\"propertyId\":\"main-saw\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/image/")));

        mockMvc.perform(get("/property/main-saw"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.propertyId").value("main-saw"))
                .andExpect(jsonPath("$.paragraph").value("Main saw building."))
                .andExpect(jsonPath("$.imageList[0].name").value("Front view"))
                .andExpect(jsonPath("$.imageList[0].sahaPhotoURL").value("https://photos.example/main-saw.jpg"))
                .andExpect(jsonPath("$.imageList[0].imageUrl").value("https://photos.example/main-saw.jpg"))
                .andExpect(jsonPath("$.imageList[0].propertyId").value("main-saw"));
    }

    @Test
    public void postImageSupportsGenericMetadataFields() throws Exception {
        mockMvc.perform(post("/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propertyId\":\"venevaja1\",\"paragraph\":\"Boat shed.\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/image")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"name\":\"Boat shed front\","
                        + "\"imageUrl\":\"https://photos.example/venevaja-front.jpg\","
                        + "\"thumbnailUrl\":\"https://photos.example/venevaja-front-thumb.jpg\","
                        + "\"caption\":\"Front side of the boat shed.\","
                        + "\"altText\":\"Old wooden boat shed seen from the front.\","
                        + "\"sortOrder\":1,"
                        + "\"source\":\"google-photos\","
                        + "\"propertyId\":\"venevaja1\""
                        + "}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/image/")));

        mockMvc.perform(get("/property/venevaja1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageList[0].name").value("Boat shed front"))
                .andExpect(jsonPath("$.imageList[0].imageUrl").value("https://photos.example/venevaja-front.jpg"))
                .andExpect(jsonPath("$.imageList[0].sahaPhotoURL").value("https://photos.example/venevaja-front.jpg"))
                .andExpect(jsonPath("$.imageList[0].thumbnailUrl").value("https://photos.example/venevaja-front-thumb.jpg"))
                .andExpect(jsonPath("$.imageList[0].caption").value("Front side of the boat shed."))
                .andExpect(jsonPath("$.imageList[0].altText").value("Old wooden boat shed seen from the front."))
                .andExpect(jsonPath("$.imageList[0].sortOrder").value(1))
                .andExpect(jsonPath("$.imageList[0].source").value("google-photos"));
    }

    @Test
    public void getPropertyByPropertyIdReturnsNotFoundWhenMissing() throws Exception {
        mockMvc.perform(get("/property/missing-property"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePropertyReturnsNoContentAndRemovesProperty() throws Exception {
        mockMvc.perform(post("/property")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"propertyId\":\"delete-me\",\"paragraph\":\"Temporary property.\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/property/delete-me"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/property/delete-me"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePropertyReturnsNotFoundWhenMissing() throws Exception {
        mockMvc.perform(delete("/property/missing-property"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteImageReturnsNoContentAndRemovesImage() throws Exception {
        mockMvc.perform(post("/image")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Delete image\",\"imageUrl\":\"https://photos.example/delete-image.jpg\",\"propertyId\":\"delete-me\"}"))
                .andExpect(status().isCreated());

        Long imageId = imgRepository.findAll().get(0).getId();

        mockMvc.perform(delete("/image/" + imageId))
                .andExpect(status().isNoContent());

        assertFalse(imgRepository.existsById(imageId));
    }

    @Test
    public void deleteImageReturnsNotFoundWhenMissing() throws Exception {
        mockMvc.perform(delete("/image/999999"))
                .andExpect(status().isNotFound());
    }
}
