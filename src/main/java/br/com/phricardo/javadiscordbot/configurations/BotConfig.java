package br.com.phricardo.javadiscordbot.configurations;

import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class BotConfig {

  @Value("${discord.bot.token}")
  private String SECURITY_TOKEN;

  @Bean
  @Primary
  public DiscordApi connecting() {
    log.info("Bot is attempting to go online...");
    return new DiscordApiBuilder()
        .setToken(SECURITY_TOKEN)
        .setWaitForServersOnStartup(false)
        .setAllIntents()
        .login()
        .whenComplete(
            (__, throwable) -> {
              if (throwable != null) {
                log.error("Failed to bring the bot online: {}", throwable.getMessage());
              } else {
                log.info("Bot is online!");
              }
            })
        .join();
  }
}
