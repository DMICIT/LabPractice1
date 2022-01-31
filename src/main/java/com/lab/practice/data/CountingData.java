package com.lab.practice.data;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CountingData {

   private String fileName;
   private String columnName;
   private String filterKey;
   private String value;

    public CountingData(String fileName, String columnName) {
        this.fileName = fileName;
        this.columnName = columnName;
    }
}
