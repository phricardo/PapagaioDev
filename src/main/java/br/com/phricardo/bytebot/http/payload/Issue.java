package br.com.phricardo.bytebot.http.payload;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Issue {
  private String title;
  private String body;
  private List<String> labels;
}
