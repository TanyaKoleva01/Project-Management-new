package com.example.projectManagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SprintState sprintState = SprintState.NOT_STARTED;

    @Min(value = 2, message = "Minimum duration is 2 weeks")
    @Max(value = 4, message = "Maximum duration is 4 weeks")
    private int durationInWeeks;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;

    @CreationTimestamp
    private Instant startDate;
}
