package br.com.dev.msiallm.service;

import br.com.dev.msiallm.dto.ActorFilms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

  private final OllamaChatModel model;
  private final ChatClient chatClient;

  public String chat(String input){
    try {
      return model.call(init(input)).getResult().getOutput().getText();
    } catch (Exception e) {
      log.error(e.toString(), e);
      return "";
    }
  }

  public String chatLivre(String input){
    try {
      return model.call(input);
    } catch (Exception e) {
      log.error(e.toString(), e);
      return "";
    }
  }

  public List<ActorFilms> filmes(){
     String message = "Generate the filmography for 5 movies for  Michael Scofield";
     return chatClient.prompt()
            .user(message)
            .call()
            .entity(new ParameterizedTypeReference<>() {
            });
  }

  public Prompt init(String input){
    String template = """
     <INST>Você é um assistente de IA especialista em UFC. Use o conteúdo fornecido para responder á pergunta.
     Se você não souber a resposta, apenas diga 'Não sei'
     conteúdo: {context}
     pergunta: {input}
    """;

    String context = """
     O UFC (Ultimate Fighting Championship) é a maior organização de artes marciais mistras do mundo.
     Ele foi fundado em 1993 e se tornou extremamente popular com lutadores como Anderson Silva, Jon Jones, Poatan, Ronda Rousey, Mauricio Shogun, Conor McGregor e Israel Adesanya.
     As lutas ocorrem em diferentes categorias de peso, e os atletas combinam técnicas de diversas artes marciais como jiu-jitsu, muay thay, judo e boxe        
    """;

    PromptTemplate promptTemplate = new PromptTemplate(template);
    return promptTemplate.create(Map.of("input", input, "context", context));
  }

}
