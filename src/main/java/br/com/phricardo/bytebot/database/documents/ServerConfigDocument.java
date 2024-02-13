package br.com.phricardo.bytebot.database.documents;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "server_config")
public class ServerConfigDocument {

  @Id
  @Setter(PRIVATE)
  private String id;

  private String serverName;
  private Long serverId;
  private Long welcomeChannelId;
}
