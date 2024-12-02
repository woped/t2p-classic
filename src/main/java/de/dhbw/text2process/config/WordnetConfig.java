package de.dhbw.text2process.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class WordnetConfig {
    @Value("${wordnet.service.host}")
    public static String wordnetHost;
    
    @Value("${wordnet.service.port}")
    public static String wordnetPort;
    
    @Value("${wordnet.service.uri}")
    public static String wordnetUri;
}
