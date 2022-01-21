package com.lab.practice.entity;

import lombok.*;

import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode


public class FileBucket {
    private MultipartFile file;
}
