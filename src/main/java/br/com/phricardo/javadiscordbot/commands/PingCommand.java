package br.com.phricardo.javadiscordbot.commands;

import lombok.NonNull;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Service;

@Service
public class PingCommand {

  public void execute(@NonNull final MessageCreateEvent event) {
    event.getChannel().sendMessage("Pong!");
  }
}
