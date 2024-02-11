package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;

import java.util.List;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class ServerInfoCommandListener extends AbstractMessageCommand {

  public ServerInfoCommandListener() {
    super("serverInfo");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
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
