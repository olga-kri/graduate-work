package ru.skypro.homework.dto;

import java.util.Objects;

import java.util.List;


import javax.annotation.Generated;



@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-16T00:47:56.416674900+05:00[Asia/Yekaterinburg]", comments = "Generator version: 7.8.0")
public class Comments {

  private Integer count;/**
   * общее количество комментариев
   */
  private List<CommentDto> results;

  public Comments(Integer count, List<CommentDto> results) {
    this.count = count;
    this.results = results;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<CommentDto> getResults() {
    return results;
  }

  public void setResults(List<CommentDto> results) {
    this.results = results;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comments comments = (Comments) o;
    return Objects.equals(this.count, comments.count) &&
        Objects.equals(this.results, comments.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comments {\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

