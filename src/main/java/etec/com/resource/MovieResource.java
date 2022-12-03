package etec.com.resource;

import etec.com.client.TmdbClient;
import etec.com.client.dto.request.GetMoviesRequest;
import etec.com.client.dto.response.GetMovieResponse;
import lombok.val;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

@Path("/movie")
public class MovieResource {

  @Inject
  TmdbClient tmdbClient;

  @GET
  @Path("/popular")
  public GetMovieResponse getPopularMovies(
          @QueryParam("language") String language,
          @QueryParam("page") Integer page
  ) throws IOException, InterruptedException {

    val movieRequest = GetMoviesRequest.builder()
            .language(language)
            .page(page)
            .build();

    return tmdbClient.getPopularMovies(movieRequest);
  }

  @GET
  public GetMovieResponse getMovies(
          @QueryParam("language") String language,
          @QueryParam("adult") Boolean includeAdult,
          @QueryParam("page") Integer page,
          @QueryParam("genres") String genres
  ) throws IOException, InterruptedException {

    val movieRequest = GetMoviesRequest.builder()
            .language(language)
            .includeAdult(includeAdult)
            .page(page)
            .withGenres(genres)
            .build();

    return tmdbClient.getMovies(movieRequest);
  }

}
