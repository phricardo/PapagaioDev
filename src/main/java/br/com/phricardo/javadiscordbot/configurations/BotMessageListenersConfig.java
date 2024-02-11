package br.com.phricardo.javadiscordbot.configurations;

import br.com.phricardo.javadiscordbot.listeners.PingCommandListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BotMessageListenersConfig {

  private final DiscordApi discordApi;

  // Listeners
  private final PingCommandListener pingCommandListener;

  @Bean
  public DiscordApi register() {
    discordApi.addListener(pingCommandListener);
    return discordApi;
  }
}
