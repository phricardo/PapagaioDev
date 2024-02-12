package br.com.phricardo.bytebot.listeners.commands;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.regex.Pattern.quote;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

@RequiredArgsConstructor
public abstract class AbstractMessageCommand implements MessageCreateListener {

  private static final String prefix = "!";
  private static final String commandPartsRegex = "[^a-zA-Z0-9" + quote(prefix) + "]";

  private final String command;

  @Override
  public void onMessageCreate(final MessageCreateEvent event) {
    final String messageContent = event.getMessageContent();
    final String cmdInput = messageContent.split(" ")[0];
    final boolean isValidCmd = cmdInput.equals(format("%s%s", prefix, command));

    final List<String> commandParts =
        stream(messageContent.split("\\s+"))
            .map(part -> part.replaceAll(commandPartsRegex, ""))
            .toList();

    if (isValidCmd) this.execute(event, commandParts);
  }

  protected abstract void execute(final MessageCreateEvent event, List<String> commandParts);
}
