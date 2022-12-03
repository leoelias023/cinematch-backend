package etec.com.client.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetMoviesRequest {

  String language;
  Boolean includeAdult;
  Integer page;
  String withGenres;

}
