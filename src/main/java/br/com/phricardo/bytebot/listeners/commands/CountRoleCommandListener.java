package br.com.phricardo.bytebot.listeners.commands;

import java.util.List;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class CountRoleCommandListener extends AbstractMessageCommand {

  public CountRoleCommandListener() {
    super("countRole");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
    final String command = commandParts.get(0);
    if (commandParts.size() == 2) {
      String roleId = commandParts.get(1);
      event
          .getServer()
          .flatMap(server -> server.getRoleById(roleId))
          .ifPresentOrElse(
              role -> handleRoleFound(event, role), () -> handleRoleNotFound(event, roleId));
    } else {
      event.getChannel().sendMessage("O comando precisa ser ``" + command + " <@ROLE>``");
    }
  }

  private void handleRoleFound(final MessageCreateEvent event, final Role role) {
    long memberCount = role.getUsers().size();
    event
        .getChannel()
        .sendMessage("A role " + role.getName() + " tem " + memberCount + " membros.");
  }

  private void handleRoleNotFound(final MessageCreateEvent event, final String roleId) {
    event.getChannel().sendMessage("Não foi possível encontrar a role com ID: " + roleId);
  }
}
