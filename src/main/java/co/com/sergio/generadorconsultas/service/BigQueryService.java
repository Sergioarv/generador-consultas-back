package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.dto.SchedulesDTO;
import co.com.sergio.generadorconsultas.utils.ConvertTo;
import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 10/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class BigQueryService {

    private final BigQuery bigQuery;

    @Autowired
    public BigQueryService(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    public List<SchedulesDTO> runQuery(String sqlQuery) throws InterruptedException {

        try {
            String encodedSqlQuery = URLEncoder.encode(sqlQuery, "UTF-8");
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(
                    "SELECT * FROM bigquery-public-data.baseball.schedules where dayNight = 'N' LIMIT 10").build();
            TableResult result = bigQuery.query(queryConfig);

            List<SchedulesDTO> resultList = new ArrayList<>();
            ConvertTo convertTo = new ConvertTo();

            for (FieldValueList fVL : result.iterateAll()) {
                SchedulesDTO schedulesDTO = convertTo.convertFieldValueListToSchedulesDTO(fVL);
                resultList.add(schedulesDTO);
            }

            return resultList;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage(), e);
        } catch (BigQueryException e) {
            // Manejar las excepciones específicas de BigQuery
            throw new RuntimeException("Error de BigQuery: " + e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("No soporta caracteres: " + e.getMessage(), e);
        } catch (ParseException e) {
            throw new RuntimeException("Error al momento de convertir la fecha: " + e.getMessage(), e);
        }
    }
}
