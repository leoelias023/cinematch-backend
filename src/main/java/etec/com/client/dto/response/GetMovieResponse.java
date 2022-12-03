package etec.com.client.dto.response;

import etec.com.dto.Movie;
import lombok.Value;

import java.util.List;

@Value
public class GetMovieResponse {

  Integer page;
  List<Movie> results;
  Integer total_pages;
  Integer total_results;

}
