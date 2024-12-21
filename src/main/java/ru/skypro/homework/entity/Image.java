package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String mediaType;
    private byte[] data;

}
