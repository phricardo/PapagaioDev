package br.com.phricardo.bytebot.listeners.register;

import br.com.phricardo.bytebot.listeners.commands.BotInfoCommandListenerAbstract;
import br.com.phricardo.bytebot.listeners.commands.PingCommandListenerAbstract;
import br.com.phricardo.bytebot.listeners.commands.ServerInfoCommandListenerAbstract;
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

  // Commands
  private final PingCommandListenerAbstract pingCommandListener;
  private final ServerInfoCommandListenerAbstract serverInfoCommandListener;
  private final BotInfoCommandListenerAbstract botInfoCommandListener;

  @Bean
  public DiscordApi register() {
    discordApi.addListener(pingCommandListener);
    discordApi.addListener(serverInfoCommandListener);
    discordApi.addListener(botInfoCommandListener);
    return discordApi;
  }
}
