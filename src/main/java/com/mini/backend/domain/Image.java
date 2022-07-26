package com.mini.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String imgFileName;

    @Column
    private String imgUrl;

    public Image(String fileName, String imgUrl) {
        this.imgFileName = fileName;
        this.imgUrl = imgUrl;
    }
}
