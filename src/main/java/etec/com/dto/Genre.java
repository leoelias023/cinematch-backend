package etec.com.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Genre {

  Integer id;
  String name;
}
