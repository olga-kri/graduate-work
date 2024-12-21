package ru.skypro.homework.dto;

import java.util.Objects;


import javax.annotation.Generated;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-16T00:47:56.416674900+05:00[Asia/Yekaterinburg]", comments = "Generator version: 7.8.0")
public class AdDTO {

  private Integer author;
  /**
   * ad's id
   */
  private String image;
  /**
   * link to the ad's image
   */

  private Integer pk;
  /**
   * ad's  id
   */
  private Integer price;
  private String title;

  public AdDTO(Integer author, String image, Integer pk, Integer price, String title) {
    this.author = author;
    this.image = image;
    this.pk = pk;
    this.price = price;
    this.title = title;
  }

  public Integer getAuthor() {
    return author;
  }

  public void setAuthor(Integer author) {
    this.author = author;
  }

  public String getImage() {
    return image;
  }
  public void setImage(String image) {
    this.image = image;
  }

  public Integer getPk() {
    return pk;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdDTO adDTO = (AdDTO) o;
    return Objects.equals(this.author, adDTO.author) &&
        Objects.equals(this.image, adDTO.image) &&
        Objects.equals(this.pk, adDTO.pk) &&
        Objects.equals(this.price, adDTO.price) &&
        Objects.equals(this.title, adDTO.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, image, pk, price, title);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ad {\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

