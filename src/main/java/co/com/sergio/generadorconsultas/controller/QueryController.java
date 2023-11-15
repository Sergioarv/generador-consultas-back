package co.com.sergio.generadorconsultas.controller;

import co.com.sergio.generadorconsultas.entity.Query;
import co.com.sergio.generadorconsultas.service.QueryService;
import co.com.sergio.generadorconsultas.utils.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/query")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class QueryController {

    @Autowired
    private QueryService queryService;

    /**
     * Método encargado dede listar todas querys de la base de datos
     *
     * @return una List con las querys de la base de datos
     */
    @GetMapping
    public ResponseEntity<GeneralResponse<List<Query>>> getAllQuerys() {

        GeneralResponse<List<Query>> response = new GeneralResponse<>();
        List<Query> data;
        HttpStatus status = HttpStatus.OK;

        data = queryService.getAllQuerys();

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if (data.size() > 1) {
                response.setMessage("Lista de querys obtenida correctamente");
            } else if (data.size() == 1) {
                response.setMessage("Query obtenida correctamente");
            } else {
                response.setSuccess(false);
                response.setMessage("La lista se encuentra vacia");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("La lista se encuentra vacia");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de buscar las querys por nombre de la query o nombre del usuario
     *
     * @param name,          nombre con la que se encuentra guardada la query
     * @param userresgister, usuario que realiza el comentario en la query
     * @param pag,           número de la página a buscar
     * @param sizePag,       cantidad de elementos por pagona
     * @return Un Page o una paginación de los resultados
     */
    @GetMapping("/filter")
    public ResponseEntity<GeneralResponse<Page<Query>>> filter(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "userregister", required = false) String userresgister,
            @RequestParam(value = "pag", defaultValue = "0", required = false) int pag,
            @RequestParam(value = "sizePag", defaultValue = "10", required = false) int sizePag
    ) {
        GeneralResponse<Page<Query>> response = new GeneralResponse<>();
        HttpStatus status = HttpStatus.OK;
        Page<Query> data;

        Pageable pageable = PageRequest.of(pag, sizePag, Sort.by("idquery").ascending());

        data = queryService.filterQuerys(name, userresgister, pageable);

        if (data != null) {
            response.setData(data);
            response.setSuccess(true);

            if (data.getContent().size() > 1) {
                response.setMessage("Lista de querys obtenida con exito");
            } else if (data.getContent().size() == 1) {
                response.setMessage("Query obtenida con exito");
            } else {
                response.setSuccess(false);
                response.setMessage("La lista de querys esta vacia");
            }
        } else {
            response.setData(null);
            response.setSuccess(false);
            response.setMessage("Hubo un error al obtener la lista de querys");
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de guardar una query
     *
     * @param query, entidad query que será guardada
     * @return la query guardada en la base de datos
     */
    @PostMapping
    public ResponseEntity<GeneralResponse<Query>> saveQuery(@RequestBody Query query) {

        System.out.println(query);

        GeneralResponse<Query> response = new GeneralResponse<>();
        Query data;
        HttpStatus status = HttpStatus.OK;

        try {
            data = queryService.saveQuery(query);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se guardo correctamente la query");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al guardar la query");
            }
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de actualizar una query
     *
     * @param query, entidad query a actualizar con los datos actualizados
     * @return La query actualizada en la base de datos
     */
    @PutMapping
    public ResponseEntity<GeneralResponse<Query>> updateQuery(@RequestBody Query query) {

        GeneralResponse<Query> response = new GeneralResponse<>();
        Query data;
        HttpStatus status = HttpStatus.OK;

        try{
            data = queryService.updateQuery(query);

            if (data != null) {
                response.setData(data);
                response.setSuccess(true);
                response.setMessage("Se guardo correctamente la query");
            } else {
                response.setData(null);
                response.setSuccess(false);
                response.setMessage("Hubo un error al actualizar la query");
            }
        }catch (Exception e){
            response.setData(null);
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

    /**
     * Método encargado de eliminar una query
     *
     * @param query, entidad a eliminar
     * @return booleano para verificar la eliminación de la query
     */
    @DeleteMapping
    public ResponseEntity<GeneralResponse<Boolean>> deleteQuery(@RequestBody Query query) {

        GeneralResponse<Boolean> response = new GeneralResponse<>();
        boolean data;
        HttpStatus status = HttpStatus.OK;

        data = queryService.deleteQuery(query);

        if (data) {
            response.setData(true);
            response.setSuccess(true);
            response.setMessage("Se elimino correctamente la query");
        } else {
            response.setData(true);
            response.setSuccess(true);
            response.setMessage("No se ha podido eliminar la query");
        }

        return new ResponseEntity<>(response, status);
    }
}
