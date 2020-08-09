package ru.tsystems.school.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "routes")
@NoArgsConstructor
@Data
public class Route extends AbstractPo implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "route_station",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "station_id")})
    private Set<Station> stations;

    @OneToMany(mappedBy = "route")
    private Set<Ticket> ticket;

}
