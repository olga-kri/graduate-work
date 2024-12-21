package ru.skypro.homework.dto;

import java.util.Objects;


import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-16T00:47:56.416674900+05:00[Asia/Yekaterinburg]", comments = "Generator version: 7.8.0")
public class CommentDTO {

  private Integer author;/**
   * id автора комментария
   */

  private String authorImage;/**
   *ссылка на аватар автора комментария
   */

  private String authorFirstName;

  private Long createdAt;/**
   *дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
   */

  private Integer pk;/**
   *id комментария
   */

  private String text;

  public CommentDTO(Integer author, String authorImage, String authorFirstName, Long createdAt, Integer pk, String text) {
    this.author = author;
    this.authorImage = authorImage;
    this.authorFirstName = authorFirstName;
    this.createdAt = createdAt;
    this.pk = pk;
    this.text = text;
  }

  public Integer getAuthor() {
    return author;
  }

  public void setAuthor(Integer author) {
    this.author = author;
  }

  public String getAuthorImage() {
    return authorImage;
  }

  public void setAuthorImage(String authorImage) {
    this.authorImage = authorImage;
  }

  public String getAuthorFirstName() {
    return authorFirstName;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getPk() {
    return pk;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentDTO commentDTO = (CommentDTO) o;
    return Objects.equals(this.author, commentDTO.author) &&
        Objects.equals(this.authorImage, commentDTO.authorImage) &&
        Objects.equals(this.authorFirstName, commentDTO.authorFirstName) &&
        Objects.equals(this.createdAt, commentDTO.createdAt) &&
        Objects.equals(this.pk, commentDTO.pk) &&
        Objects.equals(this.text, commentDTO.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, authorImage, authorFirstName, createdAt, pk, text);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    authorImage: ").append(toIndentedString(authorImage)).append("\n");
    sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

