package br.com.phricardo.javadiscordbot.listeners.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.event.server.ServerJoinEvent;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomServerJoinEvent {

  public void execute(final ServerJoinEvent event) {
    final var server = event.getServer();
    log.info(server.getName());
  }
}
