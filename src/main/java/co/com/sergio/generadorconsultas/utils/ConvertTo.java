package co.com.sergio.generadorconsultas.utils;

import co.com.sergio.generadorconsultas.dto.SchedulesDTO;
import com.google.cloud.bigquery.FieldValueList;

import java.sql.Timestamp;
import java.text.ParseException;
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
     * @param fieldValueList, lista de parametros retornado por el BigQuery
     * @return Objecto SchedulesDTO con los parametros seteados
     */
    public SchedulesDTO convertFieldValueListToSchedulesDTO(FieldValueList fieldValueList) {

        SchedulesDTO schedulesDTO = new SchedulesDTO();

        schedulesDTO.setGameId(fieldValueList.get("gameId").getStringValue());
        schedulesDTO.setGameNumber(fieldValueList.get("gameNumber").getNumericValue().intValue());
        schedulesDTO.setSeasonId(fieldValueList.get("seasonId").getStringValue());
        schedulesDTO.setYear(fieldValueList.get("year").getNumericValue().intValue());
        schedulesDTO.setType(fieldValueList.get("type").getStringValue());
        schedulesDTO.setDayNight(fieldValueList.get("dayNight").getStringValue());
        schedulesDTO.setDuration(fieldValueList.get("duration").getStringValue());
        schedulesDTO.setDurationMinutes(fieldValueList.get("duration_minutes").getNumericValue().intValue());
        schedulesDTO.setHomeTeamId(fieldValueList.get("homeTeamId").getStringValue());
        schedulesDTO.setHomeTeamName(fieldValueList.get("homeTeamName").getStringValue());
        schedulesDTO.setAwayTeamId(fieldValueList.get("awayTeamId").getStringValue());
        schedulesDTO.setAwayTeamName(fieldValueList.get("awayTeamName").getStringValue());
        schedulesDTO.setAttendance(fieldValueList.get("attendance").getNumericValue().intValue());
        schedulesDTO.setStatus(fieldValueList.get("status").getStringValue());
        schedulesDTO.setStartTime(convertTimestamp(fieldValueList.get("startTime").getNumericValue().longValue()) + " UTC");
        schedulesDTO.setCreated(convertTimestamp(fieldValueList.get("created").getNumericValue().longValue()) + " UTC");

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
}
