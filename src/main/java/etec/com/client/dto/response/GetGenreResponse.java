package etec.com.client.dto.response;

import etec.com.dto.Genre;
import lombok.Value;

import java.util.List;

@Value
public class GetGenreResponse {

  List<Genre> genres;
}
