package br.com.phricardo.bytebot.listeners.commands;

import static java.lang.String.format;

import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

@RequiredArgsConstructor
public abstract class AbstractMessageCommand implements MessageCreateListener {

  private static final String prefix = "!";
  private final String command;

  @Override
  public void onMessageCreate(final MessageCreateEvent event) {
    final Message message = event.getMessage();
    final Boolean isValidCommand =
        (Boolean) message.getContent().equalsIgnoreCase(format("%s%s", prefix, command));
    if (isValidCommand) this.execute(event);
  }

  protected abstract void execute(final MessageCreateEvent event);
}
