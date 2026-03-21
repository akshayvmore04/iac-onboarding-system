package com.iac.onboarding.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UTM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String utmCode;

    private String utmLink;

    private LocalDateTime createdAt = LocalDateTime.now();

    //  Owning side
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}