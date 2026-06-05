package br.com.dev.msiallm.dto;

import java.util.List;

public record ActorFilms(String actor, List<String> movies) {
}
