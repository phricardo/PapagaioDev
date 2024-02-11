package br.com.phricardo.javadiscordbot.listeners.commands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PingCommandListener implements MessageCreateListener {

  @Override
  public void onMessageCreate(MessageCreateEvent event) {
    final Message message = event.getMessage();
    final Boolean isValidCommand = (Boolean) message.getContent().equalsIgnoreCase("!ping");
    if (isValidCommand) this.execute(event);
  }

  private void execute(@NonNull final MessageCreateEvent event) {
    event.getChannel().sendMessage("Pong!");
  }
}
