package likelion13th.blog.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
@Configuration
public class OpenAiConfig {

    @Value("${openai.api.key}")
    private String openaikey;

    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(openaikey);
    }
}
