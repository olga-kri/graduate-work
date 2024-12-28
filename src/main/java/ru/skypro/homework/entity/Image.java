package ru.skypro.homework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table (name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer id;
    @Column(name = "file_size", nullable = false)
    private long fileSize;

    @Column(name = "media_type", nullable = false)
    private String mediaType;

    @Lob
    private byte[] data;

}
