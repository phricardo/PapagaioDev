package br.com.phricardo.bytebot.services;

import static java.util.concurrent.Executors.newScheduledThreadPool;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.javacord.api.entity.activity.ActivityType.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityStatusService {

  private final DiscordApi discordApi;
  private final String[] activityTexts = {"Se Depende, logo Dev Senior!", "O Terror do Stackoverflow", "Java? Depende!"};
  private final ActivityType[] activityTypes = {STREAMING, WATCHING, LISTENING};

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
