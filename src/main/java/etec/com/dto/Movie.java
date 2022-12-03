package etec.com.dto;

import lombok.Value;

import java.util.List;

@Value
public class Movie {

  Integer id;

  Boolean adult;
  String backdrop_path;
  List<Integer> genre_ids;
  String original_language;
  String original_title;
  String overview;
  String title;
  Double vote_average;

}
