package etec.com.client;

import com.google.gson.Gson;
import etec.com.client.dto.request.GetMoviesRequest;
import etec.com.client.dto.response.GetGenreResponse;
import etec.com.client.dto.response.GetMovieResponse;
import etec.com.dto.Genre;
import etec.com.dto.Movie;
import lombok.val;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Singleton;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

@Singleton
public class TmdbClient {

  @ConfigProperty(name = "tmdb.api.key")
  String API_KEY;

  @ConfigProperty(name = "tmdb.api.url")
  String API_URL;

  private final HttpClient httpClient = HttpClient.newHttpClient();

  public List<Genre> getGenres() throws IOException, InterruptedException {

    val uri = UriBuilder.fromUri(API_URL)
            .queryParam("api_key", API_KEY)
            .path("/3/genre/movie/list")
            .build();

    val response = httpClient.send(
            HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build(),
            HttpResponse.BodyHandlers.ofString()
    );

    val genreResponse = new Gson().fromJson(response.body(), GetGenreResponse.class);

    return genreResponse.getGenres();
  }

  public GetMovieResponse getPopularMovies(GetMoviesRequest request) throws IOException, InterruptedException {

    if (request.getPage() == null) {
      throw new IllegalArgumentException("Page must be specified");
    }

    if (request.getLanguage() == null) {
      throw new IllegalArgumentException("Language must be specified");
    }

    val uri = UriBuilder.fromUri(API_URL)
            .queryParam("api_key", API_KEY)
            .queryParam("page", request.getPage())
            .queryParam("language", request.getLanguage())
            .path("/3/movie/popular")
            .build();

    val response =httpClient.send(
            HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build(),
            HttpResponse.BodyHandlers.ofString()
    );

    return new Gson().fromJson(response.body(), GetMovieResponse.class);
  }

  public GetMovieResponse getMovies(GetMoviesRequest request) throws IOException, InterruptedException {

    if (request.getPage() == null) {
      throw new IllegalArgumentException("Page must be specified");
    }

    if (request.getLanguage() == null) {
      throw new IllegalArgumentException("Language must be specified");
    }

    val uri = UriBuilder.fromUri(API_URL)
            .queryParam("api_key", API_KEY)
            .queryParam("page", request.getPage())
            .queryParam("language", request.getLanguage())
            .path("/3/discover/movie");

    if (Objects.nonNull(request.getIncludeAdult())) {
      uri.queryParam("include_adult", request.getIncludeAdult());
    }

    if (Objects.nonNull(request.getWithGenres())) {
      uri.queryParam("with_genres", request.getWithGenres());
    }

    val response = httpClient.send(
            HttpRequest.newBuilder()
                    .GET()
                    .uri(uri.build())
                    .build(),
            HttpResponse.BodyHandlers.ofString()
    );

    return new Gson().fromJson(response.body(), GetMovieResponse.class);
  }

}
