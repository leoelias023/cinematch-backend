package etec.com.resource;

import etec.com.client.TmdbClient;
import etec.com.dto.Genre;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/genre")
public class GenreResource {

  @Inject
  TmdbClient tmdbClient;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Genre> fetchGenres() throws IOException, InterruptedException {
    return tmdbClient.getGenres();
  }

}
