package com.lab.practice.service;

import com.lab.practice.entity.Film;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CsvParceServiceTest {

    @InjectMocks
    private CsvParceService csvParceService;

    @Mock
    private FileStorageService fileStorageService;
    private String filename = "test.csv";

    @TempDir
    Path tempDir;

    Path path;
    File file;

    @BeforeEach
    public void setUp() {
        try {
            path = tempDir.resolve(filename);
        } catch (InvalidPathException ipe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
        file = path.toFile();
    }

    @Test
    public void parseCsvFileTest() throws IOException {

        FileWriter fw1 = new FileWriter(file);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        bw1.write("Title;Release Date;Color/B&W;Genre;Language;Country;Rating;Lead Actor;Director Name;Lead Actor FB Likes;Cast FB Likes;Director FB Likes;Movie FB Likes;IMDb Score (1-10);Total Reviews;Duration (min);Gross Revenue;Budget\n"+
                "Over the Hill to the Poorhouse;9/15/20;Black and White;Crime;English;USA;Not Rated;Stephen Carr;Harry F. Millarde;2;4;0;0;48;1;110;3000000;100000");
        bw1.close();
        when(fileStorageService.load(filename)).thenReturn(file.toPath());

        List<Film> result = csvParceService.parseCsvFile(filename);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Over the Hill to the Poorhouse");
    }

}