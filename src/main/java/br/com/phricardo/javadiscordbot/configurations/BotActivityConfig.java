package br.com.phricardo.javadiscordbot.configurations;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.javacord.api.entity.activity.ActivityType.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BotActivityConfig {

  private final DiscordApi discordApi;
  private final String[] activityTexts = {"phricardo.com.br", "@phricardorj", "Java"};
  private final ActivityType[] activityTypes = {LISTENING, STREAMING, WATCHING};

  @Bean
  public DiscordApi setActivity() {
    discordApi.updateActivity(activityTypes[0], activityTexts[0]);
    newScheduledThreadPool(1).scheduleAtFixedRate(this::updateActivity, 30, 30, SECONDS);
    return discordApi;
  }

  private void updateActivity() {
    int currentIndex = 0;
    currentIndex = (currentIndex + 1) % activityTexts.length;
    discordApi.updateActivity(activityTypes[currentIndex], activityTexts[currentIndex]);
  }
}
