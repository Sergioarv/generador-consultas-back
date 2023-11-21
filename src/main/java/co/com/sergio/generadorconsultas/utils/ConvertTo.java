package co.com.sergio.generadorconsultas.utils;

import co.com.sergio.generadorconsultas.dto.DatosFrecuency;
import co.com.sergio.generadorconsultas.dto.SchedulesDTO;
import com.google.cloud.bigquery.FieldValueList;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 11/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

public class ConvertTo {

    /**
     * Método encargado de convertir un ResultList a SchedulesDTO
     * @param fVL, lista de parametros retornado por el BigQuery
     * @return Objecto SchedulesDTO con los parametros seteados
     */
    public SchedulesDTO convertFieldValueListToSchedulesDTO(FieldValueList fVL) {

        SchedulesDTO schedulesDTO = new SchedulesDTO();

        schedulesDTO.setGameId(fVL.get("gameId").getStringValue());
        schedulesDTO.setGameNumber(fVL.get("gameNumber").getNumericValue().intValue());
        schedulesDTO.setSeasonId(fVL.get("seasonId").getStringValue());
        schedulesDTO.setYear(fVL.get("year").getNumericValue().intValue());
        schedulesDTO.setType(fVL.get("type").getStringValue());
        schedulesDTO.setDayNight(fVL.get("dayNight").getStringValue());
        schedulesDTO.setDuration(fVL.get("duration").getStringValue());
        schedulesDTO.setDurationMinutes(fVL.get("duration_minutes").getNumericValue().intValue());
        schedulesDTO.setHomeTeamId(fVL.get("homeTeamId").getStringValue());
        schedulesDTO.setHomeTeamName(fVL.get("homeTeamName").getStringValue());
        schedulesDTO.setAwayTeamId(fVL.get("awayTeamId").getStringValue());
        schedulesDTO.setAwayTeamName(fVL.get("awayTeamName").getStringValue());
        schedulesDTO.setAttendance(fVL.get("attendance").getNumericValue().intValue());
        schedulesDTO.setStatus(fVL.get("status").getStringValue());
        schedulesDTO.setStartTime(convertTimestamp(fVL.get("startTime").getNumericValue().longValue()) + " UTC");
        schedulesDTO.setCreated(convertTimestamp(fVL.get("created").getNumericValue().longValue()) + " UTC");

        return schedulesDTO;
    }

    /**
     * Método encargado de dar formato al Timestamp recibido desde la base de datos
     * @param timestamp, Timestamp a dar formato
     * @return String del Timestamp con el formato correcto
     */
    private String convertTimestamp(long timestamp) {

        Timestamp startTime = new Timestamp(timestamp * 1000);

        // Establecer el formato deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(startTime);
    }

    /**
     * Método encargado de convertir un ResultList a DatosFrecuency
     * @param fVL, lista de parametros retornado por el BigQuery
     * @return Objecto  DatosFrecuency con los parametros seteados
     */
    public DatosFrecuency convertFieldValueListToDatosFrecuency(FieldValueList fVL) {

        DatosFrecuency datosFrecuency = new DatosFrecuency();

        datosFrecuency.setValue(fVL.get("value").getStringValue());
        datosFrecuency.setFrecuency(Integer.parseInt(fVL.get("frecuency").getStringValue()));

        return datosFrecuency;
    }
}
