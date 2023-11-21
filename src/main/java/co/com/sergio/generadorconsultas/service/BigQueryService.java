package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.dto.*;
import co.com.sergio.generadorconsultas.utils.ConvertTo;
import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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


    /**
     * Método encargado de realizar las peticiones a BigQuery
     *
     * @param gameNumber,  numero de juegos
     * @param dayNight,    D/N horario de dia o noche
     * @param duration,    duraccion en minutos y segundos con el formato (m:ss)
     * @param status,      estado del partido, closed, canceled o unnecessary
     * @param year,        año del patido
     * @param conditional, Condicional de busqueda, en caso de buscar mas de 1 parametros
     *                     este condicional permite buscar ambos o almenos 1 de ellos
     * @param pag,         número de la página a buscar
     * @param sizePag,     cantidad de elementos por pagona
     * @return Lista de resultados "SchedulesDTO" los parametros recibidos
     * @throws InterruptedException, Si hay errores al ejecutar la consulta
     */
    public Page<SchedulesDTO> runQuery(String gameNumber, String dayNight, String duration, String status, String year, String conditional, int pag, int sizePag) throws InterruptedException {

        try {

            String sql = "SELECT * FROM bigquery-public-data.baseball.schedules";
            StringBuilder params;

            params = getParam(gameNumber, dayNight, duration, status, year, conditional);

            if (!params.isEmpty()) {
                sql += params;
            }

            // configuración y petición por medio del api
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql).build();
            TableResult result = bigQuery.query(queryConfig);

            // Lista de resultados de la api
            List<SchedulesDTO> resultList = new ArrayList<>();
            ConvertTo convertTo = new ConvertTo();

            //se itera la lista para convertirla en SchedulesDTO
            for (FieldValueList fVL : result.iterateAll()) {
                SchedulesDTO schedulesDTO = convertTo.convertFieldValueListToSchedulesDTO(fVL);
                resultList.add(schedulesDTO);
            }

            // Se genera la paginación de la lista resultante
            Pageable pageable = PageRequest.of(pag, sizePag, Sort.by("gameId").descending());
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), resultList.size());

            // Se determina el tamaño de la lista paginada y se seprada una sublista
            List<SchedulesDTO> resultListContent = resultList.subList(start, end);
            // Se genera y retorna la paginación con la sublista
            return new PageImpl<>(resultListContent, pageable, resultList.size());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage(), e);
        } catch (BigQueryException e) {
            // Manejar las excepciones específicas de BigQuery
            throw new RuntimeException("Error de BigQuery: " + e.getMessage(), e);
        }
    }

    /**
     * Método encargado de verificar cada paramtro de busqueda y generar un string
     * con la estructura de la query
     *
     * @param gameNumber,  numero de juegos
     * @param dayNight,    D/N horario de dia o noche
     * @param duration,    duraccion en minutos y segundos con el formato (m:ss)
     * @param status,      estado del partido, closed, canceled o unnecessary
     * @param year,        año del patido
     * @param conditional, Condicional de busqueda, en caso de buscar mas de 1 parametros
     *                     este condicional permite buscar ambos o almenos 1 de ellos
     * @return un string con la estructura de la query segun los parametros buscados
     */
    private StringBuilder getParam(String gameNumber, String dayNight, String duration, String status, String year, String conditional) {

        StringBuilder params = new StringBuilder();

        // verifica si se busca por parametro gamenumber
        if (gameNumber != null && !gameNumber.isEmpty()) {
            //verifica si ya hay parametros de busqueda
            // Si no hay genera la estructura con un where y el parametro
            // en caso contrario la estructura es el condicional y el parametro
            if (params.isEmpty()) {
                params.append(" where gameNumber = ").append(gameNumber);
            } else {
                params.append(" ").append(conditional).append(" gameNumber = ").append(gameNumber);
            }
        }
        // verifica si se busca por parametro dayNight
        if (dayNight != null && !dayNight.isEmpty()) {
            //verifica si ya hay parametros de busqueda
            // Si no hay genera la estructura con un where y el parametro
            // en caso contrario la estructura es el condicional y el parametro
            if (params.isEmpty()) {
                params.append(" where dayNight = '").append(dayNight).append("'");
            } else {
                params.append(" ").append(conditional).append(" dayNight = '").append(dayNight).append("'");
            }
        }
        // verifica si se busca por parametro duration
        if (duration != null && !duration.isEmpty()) {
            //verifica si ya hay parametros de busqueda
            // Si no hay genera la estructura con un where y el parametro
            // en caso contrario la estructura es el condicional y el parametro
            if (params.isEmpty()) {
                params.append(" where duration = '").append(duration).append("'");
            } else {
                params.append(" ").append(conditional).append(" duration = '").append(duration).append("'");
            }
        }
        // verifica si se busca por parametro status
        if (status != null && !status.isEmpty()) {
            //verifica si ya hay parametros de busqueda
            // Si no hay genera la estructura con un where y el parametro
            // en caso contrario la estructura es el condicional y el parametro
            if (params.isEmpty()) {
                params.append(" where status = '").append(status).append("'");
            } else {
                params.append(" ").append(conditional).append(" status = '").append(status).append("'");
            }
        }
        // verifica si se busca por parametro year
        if (year != null && !year.isEmpty()) {
            //verifica si ya hay parametros de busqueda
            // Si no hay genera la estructura con un where y el parametro
            // en caso contrario la estructura es el condicional y el parametro
            if (params.isEmpty()) {
                params.append(" where year = ").append(year);
            } else {
                params.append(" ").append(conditional).append(" year = ").append(year);
            }
        }

        return params;
    }

    /**
     * Método encargado de traer los datos a graficar desde apiBigQuery
     * @return un Dto Datos a graficar
     */
    public DatosaGraficarDTO graficas() {

        DatosaGraficarDTO datosaGraficarDTO = new DatosaGraficarDTO();
        List<DatosFrecuency> result;
        try {
            result = queryCustom("SELECT status AS value, COUNT(*) AS frecuency FROM bigquery-public-data.baseball.schedules GROUP BY status ORDER BY status ASC");
            datosaGraficarDTO.setStatusGameList(result);

            result = queryCustom("SELECT daynight AS value, COUNT(*) AS frecuency FROM bigquery-public-data.baseball.schedules GROUP BY daynight ORDER BY daynight ASC");
            datosaGraficarDTO.setDayNightGames(result);

            result = queryCustom("SELECT duration AS value, COUNT(*) AS frecuency FROM bigquery-public-data.baseball.schedules GROUP BY duration ORDER BY duration ASC");
            datosaGraficarDTO.setDurationFrequencyList(result);

            return datosaGraficarDTO;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método encargado de realizar las consultas al api BigQuery de Google
     * @param sql, consulta a realizar
     * @return List de datosFrecuency
     * @throws InterruptedException
     */
    private List<DatosFrecuency> queryCustom(String sql) throws InterruptedException {

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(sql).build();
        TableResult result = bigQuery.query(queryConfig);

        ConvertTo convertTo = new ConvertTo();

        List<DatosFrecuency> resultQuery = new ArrayList<>();

        DatosFrecuency datosFrecuency = null;
        for (FieldValueList fVL : result.iterateAll()) {
            datosFrecuency = convertTo.convertFieldValueListToDatosFrecuency(fVL);
            resultQuery.add(datosFrecuency);
        }

        return resultQuery;
    }
}
