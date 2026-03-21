package com.iac.onboarding.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int clicks = 0;

    private int signups = 0;

    //  Owning side
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}