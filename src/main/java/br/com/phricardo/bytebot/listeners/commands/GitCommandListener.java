package br.com.phricardo.bytebot.listeners.commands;

import static br.com.phricardo.bytebot.utils.Constants.CUSTOM_COLOR;

import java.util.List;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class GitCommandListener extends AbstractMessageCommand {

  public GitCommandListener() {
    super("git");
  }

  @Override
  protected void execute(final MessageCreateEvent event, List<String> commandParts) {
    final var embed =
        new EmbedBuilder()
            .setColor(CUSTOM_COLOR)
            .setTitle("Comandos do Git")
            .setDescription("Aqui estão alguns comandos úteis do Git:")
            .addInlineField(
                "$ git init [nome-do-projeto]",
                "Cria um novo repositório local com um nome especificado")
            .addInlineField(
                "$ git clone [url]", "Baixa um projeto e seu histórico de versão inteiro")
            .addInlineField(
                "$ git stash", "Armazena temporariamente todos os arquivos monitorados modificados")
            .addInlineField("$ git status", "Revise edições e crie uma transação de commit")
            .addInlineField(
                "$ git add [arquivo]",
                "Faz o snapshot de um arquivo na preparação para versionamento")
            .addInlineField(
                "$ git commit -m \"[mensagem]\"",
                "Grava o snapshot permanentemente do arquivo no histórico de versão")
            .addInlineField("$ git branch", "Lista todos os branches locais no repositório atual")
            .addInlineField("$ git branch [nome-branch]", "Cria uma nova branch")
            .addInlineField(
                "$ git switch -c [nome-branch]",
                "Muda para a branch especificada e atualiza o diretório de trabalho")
            .addInlineField(
                "$ git merge [nome-branch]",
                "Combina o histórico da branch especificada a branch atual")
            .addInlineField(
                "$ git push [alias] [branch]",
                "Envia todos os commits do branch local para o GitHub")
            .addInlineField("$ git pull", "Baixa o histórico e incorpora as mudanças");

    event.getChannel().sendMessage(embed);
  }
}
