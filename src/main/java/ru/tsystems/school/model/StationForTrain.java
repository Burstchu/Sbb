package ru.tsystems.school.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stations_for_trains")
@Data
public class StationForTrain extends AbstractPo implements Serializable {

    @OneToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @OneToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;

}
