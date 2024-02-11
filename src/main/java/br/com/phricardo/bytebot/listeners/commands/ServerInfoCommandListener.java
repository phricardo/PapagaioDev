package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerInfoCommandListener implements MessageCreateListener {

  @Override
  public void onMessageCreate(MessageCreateEvent event) {
    final Message message = event.getMessage();
    final Boolean isValidCommand = (Boolean) message.getContent().equalsIgnoreCase("!serverInfo");
    if (isValidCommand) this.execute(event);
  }

  private void execute(@NonNull final MessageCreateEvent event) {
    event
        .getServer()
        .ifPresent(
            server -> {
              final String serverName = server.getName();
              final int membersCount = getUsersCount(server);
              final int botCount = getBotCount(server);
              final var embed =
                  new EmbedBuilder()
                      .setColor(CUSTOM_COLOR)
                      .setTitle("Informações do Servidor")
                      .setDescription(
                          "Aqui estão as informações do servidor **" + serverName + "**")
                      .addField("Total de Membros", Integer.toString(membersCount), true)
                      .addField("Total de Bots", Integer.toString(botCount), true);

              event.getChannel().sendMessage(embed);
            });
  }

  private int getUsersCount(final Server server) {
    return (int) server.getMembers().stream().filter(member -> !member.isBot()).count();
  }

  private int getBotCount(Server server) {
    return (int) server.getMembers().stream().filter(User::isBot).count();
  }
}
