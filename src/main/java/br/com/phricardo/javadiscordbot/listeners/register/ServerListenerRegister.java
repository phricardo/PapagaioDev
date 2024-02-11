package br.com.phricardo.javadiscordbot.listeners.register;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ServerListenerRegister {

  private final DiscordApi discordApi;
  private static final long serverWelcomeChannelId = 968360066396856373L;

  @Bean
  public DiscordApi registerServerListener() {
    discordApi.addServerMemberJoinListener(this::serverWelcomeMessageEvent);
    return discordApi;
  }

  private void serverWelcomeMessageEvent(final ServerMemberJoinEvent event) {
    final var server = event.getServer();
    final var user = event.getUser().getNicknameMentionTag();
    final var channel = server.getTextChannelById(serverWelcomeChannelId);
    channel.ifPresent(c -> c.sendMessage(format("Seja bem-vindo(a) ao nosso Servidor, %s!", user)));
  }
}
