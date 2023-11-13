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

    @GetMapping
    public ResponseEntity<GeneralResponse<List<SchedulesDTO>>> runQuery(
            @RequestParam("sqlQuery") String sqlQuery
    ) {

        GeneralResponse<List<SchedulesDTO>> response = new GeneralResponse<>();
        List<SchedulesDTO> data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = bigQueryService.runQuery(sqlQuery);

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

        return new ResponseEntity<>(response, status);
    }
}
