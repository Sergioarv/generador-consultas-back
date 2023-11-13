package co.com.sergio.generadorconsultas.utils;

import co.com.sergio.generadorconsultas.dto.SchedulesDTO;
import com.google.cloud.bigquery.FieldValueList;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 11/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

public class ConvertTo {

    public SchedulesDTO convertFieldValueListToSchedulesDTO(FieldValueList fieldValueList) throws ParseException {

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
        schedulesDTO.setCreated(new Timestamp(fieldValueList.get("created").getTimestampValue() * 1000));

        long startTimeValue = fieldValueList.get("startTime").getNumericValue().longValue();
        Timestamp startTime = new Timestamp(startTimeValue*1000);

        // Establecer el formato deseado
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDateTime = dateFormat.format(startTime);

        System.out.println(schedulesDTO.getGameId() +"-" +formattedDateTime);

        schedulesDTO.setStartTime(new Timestamp(fieldValueList.get("created").getTimestampValue() * 1000));

        return schedulesDTO;
    }
}
