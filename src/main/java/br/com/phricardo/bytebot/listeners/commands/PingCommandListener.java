package br.com.phricardo.bytebot.listeners.commands;

import java.util.List;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class PingCommandListener extends AbstractMessageCommand {

  public PingCommandListener() {
    super("ping");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
    event.getChannel().sendMessage("Pong!");
  }
}
