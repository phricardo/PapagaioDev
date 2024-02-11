package br.com.phricardo.bytebot.listeners.register;

import br.com.phricardo.bytebot.listeners.commands.PingCommandListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageListenerRegister {

  private final DiscordApi discordApi;

  // Listeners
  private final PingCommandListener pingCommandListener;

  @Bean
  public DiscordApi register() {
    discordApi.addListener(pingCommandListener);
    return discordApi;
  }
}
