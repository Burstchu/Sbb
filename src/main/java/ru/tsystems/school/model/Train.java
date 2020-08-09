package ru.tsystems.school.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "trains")
@NoArgsConstructor
@Data
public class Train extends AbstractPo implements Serializable {

    @Column(name = "number", nullable = false, unique = true)
    private String trainNumber;

    @Column(name = "seats_amount", nullable = false)
    private int seatsAmount;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "stations_for_trains",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private Set<Station> stations;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tickets",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private Set<Passenger> passengers;

    @OneToMany (mappedBy = "train")
    private Set<Schedule> schedules;

    public Train(String trainNumber, int seatsAmount) {
        this.trainNumber = trainNumber;
        this.seatsAmount = seatsAmount;
    }
}
