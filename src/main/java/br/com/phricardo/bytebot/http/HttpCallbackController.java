package br.com.phricardo.bytebot.http;

import static java.lang.String.join;

import br.com.phricardo.bytebot.http.payload.Issue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HttpCallbackController {

  private final DiscordApi discordApi;
  private static final long channelId = 1199666411350540308L;

  @PostMapping("/callback/jobs")
  public Issue getIssuesJobs(@RequestBody final Issue issue) {
    discordApi
        .getTextChannelById(channelId)
        .ifPresent(
            channel -> {
              channel.sendMessage(createEmbedMessage(issue));
            });

    return issue;
  }

  private EmbedBuilder createEmbedMessage(@NonNull final Issue issue) {
    return new EmbedBuilder()
        .setTitle(issue.getTitle())
        .setDescription(issue.getBody())
        .addField("Labels", join(", ", issue.getLabels()));
  }
}
