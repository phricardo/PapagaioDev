package br.com.phricardo.javadiscordbot.listeners;

import br.com.phricardo.javadiscordbot.commands.PingCommand;
import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PingCommandListener implements MessageCreateListener {

  private final PingCommand pingCommand;

  @Override
  public void onMessageCreate(MessageCreateEvent event) {
    final Message message = event.getMessage();
    final Boolean isValidCommand = (Boolean) message.getContent().equalsIgnoreCase("!ping");
    if (isValidCommand) pingCommand.execute(event);
  }
}
