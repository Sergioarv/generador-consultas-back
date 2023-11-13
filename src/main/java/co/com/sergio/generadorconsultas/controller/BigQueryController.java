package co.com.sergio.generadorconsultas.controller;

import co.com.sergio.generadorconsultas.dto.SchedulesDTO;
import co.com.sergio.generadorconsultas.service.BigQueryService;
import co.com.sergio.generadorconsultas.utils.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 10/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@RestController
@RequestMapping("/bigquery")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BigQueryController {

    private final BigQueryService bigQueryService;

    public BigQueryController(BigQueryService bigQueryService) {
        this.bigQueryService = bigQueryService;
    }

    /**
     * Método encargado de realizar las peticiones a BigQuery
     *
     * @param gameNumber, numero de juegos
     * @param dayNight, D/N horario de dia o noche
     * @param duration, duraccion en minutos y segundos con el formato (m:ss)
     * @param status, estado del partido, closed, canceled o unnecessary
     * @param year, año del patido
     *
     * @return, Lista de resultados "SchedulesDTO" los parametros recibidos
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<SchedulesDTO>>> runQuery(
            @RequestParam(value = "gameNumber", required = false) String gameNumber,
            @RequestParam(value = "dayNight", required = false) String dayNight,
            @RequestParam(value = "duration", required = false) String duration,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "year", required = false) String year
    ) {

        GeneralResponse<List<SchedulesDTO>> response = new GeneralResponse<>();
        List<SchedulesDTO> data;
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            data = bigQueryService.runQuery(gameNumber);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);

                if (data.size() > 1) {
                    response.setMessage("Lista de horarios obtenida correctamente");
                } else if (data.size() == 1) {
                    response.setMessage("horarios obtenida correctamente");
                } else {
                    response.setSuccess(false);
                    response.setMessage("La lista de horarios se encuentra vacia");
                }
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("La lista de horarios se encuentra vacia");
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
