package com.mua.backupbymaifee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;

    @Lob
    private String absolutePath;
    @Lob
    private String originalAbsolutePath;

}
