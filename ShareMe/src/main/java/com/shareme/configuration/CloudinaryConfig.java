package com.shareme.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "dasy5cbgm",
                "api_key", "118753285436459",
                "api_secret", "hiGGXIOzmKBr0_DN_Acblj-kWsc"));
    }
}
