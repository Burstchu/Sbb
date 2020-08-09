package ru.tsystems.school.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stations")
@Data
public class Station extends AbstractPo implements Serializable {

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "stations_for_trains",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "train_id")
    )
    private Set<Train> trains;

    @OneToMany (mappedBy = "station")
    @ToString.Exclude
    private Set<Schedule> schedules;

    @ManyToMany(mappedBy = "stations")
    private Set<Route> routes;

}
