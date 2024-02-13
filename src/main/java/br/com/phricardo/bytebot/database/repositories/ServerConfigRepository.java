package br.com.phricardo.bytebot.database.repositories;

import br.com.phricardo.bytebot.database.documents.ServerConfigDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerConfigRepository extends MongoRepository<ServerConfigDocument, String> {

  Optional<ServerConfigDocument> findByServerId(final Long serverId);
}
