package ru.skypro.homework.dto;

import java.util.Objects;
import java.util.List;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-16T00:47:56.416674900+05:00[Asia/Yekaterinburg]", comments = "Generator version: 7.8.0")
public class Ads {

  private Integer count;/**
   * общее количество объявлений
   */
  private List<AdDto> results;

  public Ads() {
    this.count = count;
    this.results = results;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<AdDto> getResults() {
    return results;
  }

  public void setResults(List<AdDto> results) {
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
    Ads ads = (Ads) o;
    return Objects.equals(this.count, ads.count) &&
        Objects.equals(this.results, ads.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ads {\n");
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

