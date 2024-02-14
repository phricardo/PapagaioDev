package br.com.phricardo.bytebot.listeners.register;

import br.com.phricardo.bytebot.listeners.commands.*;
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
  private final PingCommandListener pingCommandListener;
  private final ServerInfoCommandListener serverInfoCommandListener;
  private final BotInfoCommandListener botInfoCommandListener;
  private final CountRoleCommandListener countRoleCommandListener;
  private final UserInfoCommandListener userInfoCommandListener;
  private final GitCommandListener gitCommandListener;

  @Bean
  public DiscordApi register() {
    discordApi.addListener(pingCommandListener);
    discordApi.addListener(serverInfoCommandListener);
    discordApi.addListener(botInfoCommandListener);
    discordApi.addListener(countRoleCommandListener);
    discordApi.addListener(userInfoCommandListener);
    discordApi.addListener(gitCommandListener);
    return discordApi;
  }
}
