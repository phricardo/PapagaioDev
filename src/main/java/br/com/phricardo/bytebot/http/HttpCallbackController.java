package br.com.phricardo.bytebot.http;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;
import static java.lang.String.join;

import br.com.phricardo.bytebot.http.payload.Issue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HttpCallbackController {

  private final DiscordApi discordApi;

  private static final List<Long> channelIds = of(1199666411350540308L, 1018175254570487890L);

  //  @PostMapping("/callback/jobs")
  //  public Issue getIssuesJobs(@RequestBody final Issue issue) {
  //    channelIds.forEach(channelId -> sendMessage(channelId, issue));
  //    return issue;
  //  }

  private void sendMessage(@NonNull final Long channelId, @NonNull final Issue issue) {
    discordApi
        .getTextChannelById(channelId)
        .ifPresent(channel -> channel.sendMessage(createEmbedMessage(issue)));
  }

  private EmbedBuilder createEmbedMessage(@NonNull final Issue issue) {
    return new EmbedBuilder()
        .setColor(CUSTOM_COLOR)
        .setTitle(issue.getTitle())
        .setDescription(issue.getBody())
        .addField("Skills", join(", ", issue.getLabels()));
  }
}
