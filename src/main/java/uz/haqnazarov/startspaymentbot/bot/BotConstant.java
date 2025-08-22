package uz.haqnazarov.startspaymentbot.bot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "bot", ignoreUnknownFields = false)
@Configuration
public class BotConstant {
    private String token;
}
