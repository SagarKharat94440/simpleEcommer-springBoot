package com.example.ecommer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String name;
    private String description;
    private  String brand;
    private BigDecimal price;
    private  String category;

    private Date releaseDate;
    private  boolean productAvailable;
    private  int stockQuantity;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;


    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    public byte[] getImageDate() {
        return imageDate;
    }
}
