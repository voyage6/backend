package com.mini.backend.repository;

import com.mini.backend.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImgUrl(String imagepath);
}
