package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;

import java.util.List;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class BotInfoCommandListener extends AbstractMessageCommand {

  public BotInfoCommandListener() {
    super("botInfo");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
    // final var userAuthor = event.getMessageAuthor();
    final var bot = event.getApi().getYourself();
    final var botName = bot.getName();
    final var botAvatarUrl = bot.getAvatar().getUrl().toString();
    int serverCount = event.getApi().getServers().size();

    final var embed =
        new EmbedBuilder()
            .setAuthor(bot)
            .setColor(CUSTOM_COLOR)
            .setTitle("Informações do " + botName)
            .setDescription("Aqui estão as informações do **" + botName + "**")
            .addField("Número de Servidores", String.valueOf(serverCount), true)
            .addField("Criador", "@phricardorj", true)
            .addField("Stacks", "Java; Spring; Docker; PostgreSQL;", false)
            .addField(
                "Github", "[Acessar Repositório](https://github.com/phricardorj/ByteBot)", true)
            .addField("versão", "v1 Alpha", true)
            .setThumbnail(botAvatarUrl);

    event.getChannel().sendMessage(embed);
  }
}
