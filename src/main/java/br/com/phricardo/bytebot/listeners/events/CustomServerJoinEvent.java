package br.com.phricardo.bytebot.listeners.events;

import br.com.phricardo.bytebot.database.SaveOrUpdateServerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.event.server.ServerJoinEvent;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomServerJoinEvent {

  private final SaveOrUpdateServerConfig saveOrUpdateServerConfig;

  public void execute(final ServerJoinEvent event) {
    final var server = event.getServer();
    saveOrUpdateServerConfig.execute(server);
    log.info(server.getName());
  }
}
