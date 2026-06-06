package com.own.prac.saha;

import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.repository.PropertyRepository;
import com.own.prac.saha.repository.TextRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SahaDevDataTests {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TextRepository textRepository;

    @Test
    public void h2ProfileSeedsFrontendDevelopmentContent() {
        assertTrue(textRepository.count() > 0);

        Optional<PropertyContent> sauna = propertyRepository.findOneByPropertyId("sauna1");
        Optional<PropertyContent> venevaja = propertyRepository.findOneByPropertyId("venevaja1");

        assertTrue(sauna.isPresent());
        assertTrue(venevaja.isPresent());
        assertFalse(sauna.get().getImageList().isEmpty());
        assertEquals(
                sauna.get().getImageList().get(0).getImageUrl(),
                sauna.get().getImageList().get(0).getSahaPhotoURL());
    }
}
