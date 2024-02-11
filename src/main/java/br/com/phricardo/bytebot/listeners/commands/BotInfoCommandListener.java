package br.com.phricardo.bytebot.listeners.commands;

import lombok.RequiredArgsConstructor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotInfoCommandListener implements MessageCreateListener {

  @Override
  public void onMessageCreate(MessageCreateEvent event) {}
}
