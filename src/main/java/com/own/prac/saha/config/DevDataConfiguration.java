package com.own.prac.saha.config;

import com.own.prac.saha.entity.ImgContent;
import com.own.prac.saha.entity.PropertyContent;
import com.own.prac.saha.entity.TextContent;
import com.own.prac.saha.repository.PropertyRepository;
import com.own.prac.saha.repository.TextRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("h2")
public class DevDataConfiguration {

    @Bean
    public CommandLineRunner seedDevData(
            TextRepository textRepository,
            PropertyRepository propertyRepository) {
        return args -> {
            if (textRepository.count() == 0) {
                textRepository.save(text(
                        "intro",
                        "Welcome to the old saw complex. This local H2 content is sample data for backend and frontend development."));
            }

            if (!propertyRepository.findOneByPropertyId("sauna1").isPresent()) {
                propertyRepository.save(property(
                        "sauna1",
                        "The sauna building is one of the small structures connected to the saw complex story.",
                        image(
                                "Sauna front view",
                                "https://picsum.photos/seed/saha-sauna-front/1200/800",
                                "https://picsum.photos/seed/saha-sauna-front/320/220",
                                "Front side of the sauna building.",
                                "Old wooden sauna building seen from the front.",
                                1,
                                "sample-placeholder")));
            }

            if (!propertyRepository.findOneByPropertyId("venevaja1").isPresent()) {
                propertyRepository.save(property(
                        "venevaja1",
                        "The boat shed keeps another piece of the site history visible near the water.",
                        image(
                                "Boat shed front view",
                                "https://picsum.photos/seed/saha-venevaja-front/1200/800",
                                "https://picsum.photos/seed/saha-venevaja-front/320/220",
                                "Front side of the boat shed.",
                                "Old wooden boat shed seen from the front.",
                                1,
                                "sample-placeholder")));
            }
        };
    }

    private TextContent text(String type, String paragraph) {
        TextContent content = new TextContent();
        content.setType(type);
        content.setParagraph(paragraph);
        return content;
    }

    private PropertyContent property(String propertyId, String paragraph, ImgContent... images) {
        PropertyContent content = new PropertyContent();
        content.setPropertyId(propertyId);
        content.setParagraph(paragraph);
        content.setImageList(Arrays.asList(images));
        return content;
    }

    private ImgContent image(
            String name,
            String imageUrl,
            String thumbnailUrl,
            String caption,
            String altText,
            Integer sortOrder,
            String source) {
        ImgContent image = new ImgContent();
        image.setName(name);
        image.setImageUrl(imageUrl);
        image.setThumbnailUrl(thumbnailUrl);
        image.setCaption(caption);
        image.setAltText(altText);
        image.setSortOrder(sortOrder);
        image.setSource(source);
        return image;
    }
}
