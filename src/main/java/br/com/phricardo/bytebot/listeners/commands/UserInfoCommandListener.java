package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserInfoCommandListener extends AbstractMessageCommand {

  private static final String BASE_URL_HTTP_API = "https://bytebot.phricardo.com.br";

  public UserInfoCommandListener() {
    super("userInfo");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
    try {
      final String command = commandParts.get(0);
      final String userId =
          commandParts.size() == 2
              ? commandParts.get(1)
              : valueOf(event.getMessageAuthor().getId());
      final User user = event.getApi().getUserById(userId).get();
      final String creationAccountDiscord =
          ofInstant(user.getCreationTimestamp(), systemDefault())
              .format(ofPattern("dd/MM/yy 'às' HH:mm"));
      final String linkUserAPI =
          format("[Acessar API Restful](%s/user/data?userId=%s)", BASE_URL_HTTP_API, user.getId());

      final var embed =
          new EmbedBuilder()
              .setAuthor(user)
              .setColor(CUSTOM_COLOR)
              .setThumbnail(user.getAvatar())
              .addField("ID", valueOf(user.getId()), false)
              .addField("Usuário", user.getName(), false)
              .addField("É bot?", user.isBot() ? "Sim" : "Não", false)
              .addField("Status", getStatusString(user.getStatus()), false)
              .addField("Obtenha em JSON com minha API", linkUserAPI)
              .addField("Entrou no Discord em", creationAccountDiscord, false);

      event.getChannel().sendMessage(embed);
    } catch (final Exception exception) {
      log.debug(exception.getLocalizedMessage(), exception);
    }
  }

  private String getStatusString(final UserStatus status) {
    return switch (status) {
      case ONLINE -> "Online";
      case IDLE -> "Ausente";
      case DO_NOT_DISTURB -> "Não Perturbar";
      case INVISIBLE -> "Invisível";
      case OFFLINE -> "Offline";
      default -> "Desconhecido";
    };
  }
}
