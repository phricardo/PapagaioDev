package br.com.phricardo.bytebot.listeners.register;

import br.com.phricardo.bytebot.listeners.events.CustomServerJoinEvent;
import br.com.phricardo.bytebot.listeners.events.ServerWelcomeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ServerListenerRegister {

  private final DiscordApi discordApi;
  private final CustomServerJoinEvent customServerJoinEvent;
  private final ServerWelcomeEvent serverWelcomeEvent;

  @Bean
  public DiscordApi registerServerListener() {
    discordApi.addServerJoinListener(customServerJoinEvent::execute);
    discordApi.addServerMemberJoinListener(serverWelcomeEvent::execute);
    return discordApi;
  }
}
