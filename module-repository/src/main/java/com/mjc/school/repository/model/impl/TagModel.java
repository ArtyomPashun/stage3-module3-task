package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 15)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<NewsModel> newsModelList = new ArrayList<>();
}
