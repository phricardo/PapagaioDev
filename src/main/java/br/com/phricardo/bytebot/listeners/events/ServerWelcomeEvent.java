package br.com.phricardo.bytebot.listeners.events;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerWelcomeEvent {

  private static final long serverWelcomeChannelId = 1199662058103394315L;

  public void execute(final ServerMemberJoinEvent event) {
    final var server = event.getServer();
    final var user = event.getUser().getNicknameMentionTag();
    final var channel = server.getTextChannelById(serverWelcomeChannelId);
    channel.ifPresent(c -> c.sendMessage(format("Seja bem-vindo(a) ao nosso Servidor, %s!", user)));
  }
}
