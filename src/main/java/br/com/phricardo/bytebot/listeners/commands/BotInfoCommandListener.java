package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotInfoCommandListener implements MessageCreateListener {

  @Override
  public void onMessageCreate(final MessageCreateEvent event) {
    final Message message = event.getMessage();
    final Boolean isValidCommand = (Boolean) message.getContent().equalsIgnoreCase("!botInfo");
    if (isValidCommand) this.execute(event);
  }

  private void execute(@NonNull final MessageCreateEvent event) {
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
