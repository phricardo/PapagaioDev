package br.com.phricardo.bytebot.http;

import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.user.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DiscordBotController {

  private final DiscordApi discordApi;

  @GetMapping("/")
  public String getStatusAPI() {
    return "OK";
  }

  @GetMapping("/user/data")
  public HashMap<String, Object> getUserInfo(@RequestParam(value = "userId") Long userId) {
    final HashMap<String, Object> response = new LinkedHashMap<>();
    try {
      final User user = discordApi.getUserById(userId).get();
      response.put("isOnline", user.getStatus().getStatusString().equals("online") || user.getStatus().getStatusString().equals("dnd"));
      response.put("avatar", user.getAvatar().getUrl().toString());
      response.put("status", user.getStatus().getStatusString().toUpperCase());
    } catch (final Exception exception) {
      log.debug(exception.getLocalizedMessage(), exception);
      response.put("message", "There was a problem getting your username!");
    }
    return response;
  }
}
