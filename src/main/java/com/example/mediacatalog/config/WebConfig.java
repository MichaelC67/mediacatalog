package com.example.mediacatalog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = uploadDir;
        // Ensure location is an absolute filesystem URL starting with file:
        if (!location.startsWith("file:")) {
            // normalize relative paths to absolute within working dir
            if (!location.startsWith("/")) {
                location = "file:./" + location;
            } else {
                location = "file:" + location;
            }
        }
        if (!location.endsWith("/")) {
            location = location + "/";
        }
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}
