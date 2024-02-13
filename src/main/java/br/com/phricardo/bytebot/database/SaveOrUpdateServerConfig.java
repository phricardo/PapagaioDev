package br.com.phricardo.bytebot.database;

import br.com.phricardo.bytebot.database.documents.ServerConfigDocument;
import br.com.phricardo.bytebot.database.repositories.ServerConfigRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.server.Server;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveOrUpdateServerConfig {

  private final ServerConfigRepository serverConfigRepository;

  public void execute(@NonNull final Server server) {
    final ServerConfigDocument serverConfig =
        serverConfigRepository
            .findByServerId(server.getId())
            .orElse(ServerConfigDocument.builder().build());

    serverConfig.setServerId(server.getId());
    serverConfig.setServerName(server.getName());
    serverConfig.setWelcomeChannelId(null);

    serverConfigRepository.save(serverConfig);
  }
}
