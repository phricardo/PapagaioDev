package br.com.phricardo.bytebot.http;

import br.com.phricardo.bytebot.http.payload.Issue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HttpCallbackController {

  @PostMapping("/callback/jobs")
  public String getIssuesJobs(@RequestBody Issue issue) {
    return issue.toString();
  }
}
