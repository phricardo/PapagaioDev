package br.com.phricardo.bytebot.listeners.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class PingCommandListenerAbstract extends AbstractMessageCommand {

  public PingCommandListenerAbstract() {
    super("ping");
  }

  @Override
  protected void execute(final MessageCreateEvent event) {
    event.getChannel().sendMessage("Pong!");
  }
}
