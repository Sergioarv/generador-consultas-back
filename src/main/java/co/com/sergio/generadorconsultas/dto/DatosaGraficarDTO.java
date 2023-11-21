package co.com.sergio.generadorconsultas.dto;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 20/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

public class DatosaGraficarDTO {

    public List<DatosFrecuency> statusGameList;
    public List<DatosFrecuency> dayNightGames;
    public List<DatosFrecuency> durationFrequencyList;

    public List<DatosFrecuency> getStatusGameList() {
        return statusGameList;
    }

    public void setStatusGameList(List<DatosFrecuency> statusGameList) {
        this.statusGameList = statusGameList;
    }

    public List<DatosFrecuency> getDayNightGames() {
        return dayNightGames;
    }

    public void setDayNightGames(List<DatosFrecuency> dayNightGames) {
        this.dayNightGames = dayNightGames;
    }

    public List<DatosFrecuency> getDurationFrequencyList() {
        return durationFrequencyList;
    }

    public void setDurationFrequencyList(List<DatosFrecuency> durationFrequencyList) {
        this.durationFrequencyList = durationFrequencyList;
    }
}
