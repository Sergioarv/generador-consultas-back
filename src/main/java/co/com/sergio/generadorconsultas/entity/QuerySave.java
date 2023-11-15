package co.com.sergio.generadorconsultas.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 14/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/
@Entity
@Table(name = "querysave")
public class QuerySave implements Serializable {

    @Id
    @Column(name = "idquerysave", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idquerysave;
    private String gamenumber;
    private String daynight;
    private String duration;
    private String status;
    private String year;
    @OneToOne(mappedBy = "querysave")
    private Query query;

    /***********************/
    /** Getter and Setter **/
    /***********************/

    public int getIdquerysave() {
        return idquerysave;
    }

    public void setIdquerysave(int idquerysave) {
        this.idquerysave = idquerysave;
    }

    public String getGamenumber() {
        return gamenumber;
    }

    public void setGamenumber(String gamenumber) {
        this.gamenumber = gamenumber;
    }

    public String getDaynight() {
        return daynight;
    }

    public void setDaynight(String daynight) {
        this.daynight = daynight;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
