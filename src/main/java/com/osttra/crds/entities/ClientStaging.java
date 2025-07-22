package com.osttra.crds.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientStaging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String data;

    @OneToOne(cascade = CascadeType.ALL)
    private ReviewTask reviewTask;
}
