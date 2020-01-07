package com.application.app.recipePicture;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "recipe_picture")
public class RecipePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Lob
    private byte[] picture;

    @NotNull
    @Column(name = "main_picture")
    private Boolean mainPicture;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private Recipe recipe;
}
