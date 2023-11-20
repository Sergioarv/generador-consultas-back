package co.com.sergio.generadorconsultas.service;

import co.com.sergio.generadorconsultas.entity.Comment;
import co.com.sergio.generadorconsultas.entity.Query;
import co.com.sergio.generadorconsultas.repository.CommentRepository;
import co.com.sergio.generadorconsultas.repository.QueryRepository;
import co.com.sergio.generadorconsultas.repository.QuerySaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 09/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    QuerySaveRepository querySaveRepository;

    @Autowired
    CommentRepository commentRepository;

    /**
     * Método encargado dede listar todas querys de la base de datos
     *
     * @return una List con las querys de la base de datos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Query> getAllQuerys() {
        return queryRepository.findAll();
    }

    /**
     * Método encargado de buscar las querys por nombre de la query o nombre del usuario
     *
     * @param name,     nombre con la que se encuentra guardada la query
     * @param createby, usuario que crea la query
     * @param pageable, parametro para generar una paginación de la información buscada
     * @return Un Page o una paginación de los resultados
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Query> filterQuerys(String name, String createby, Pageable pageable) {

        Page<Query> resultList;

        if (name != null && createby != null) {
            resultList = queryRepository.filterNameAndUser(name, createby, pageable);
        } else if (name != null) {
            resultList = queryRepository.filterName(name, pageable);
        } else if (createby != null) {
            resultList = queryRepository.filterUser(createby, pageable);
        } else {
            resultList = queryRepository.findAll(pageable);
        }

        return resultList;
    }

    /**
     * Método encargado de guardar una query
     *
     * @param query, entidad query que será guardada
     * @return la query guardada en la base de datos
     */
    @Override
    @Transactional
    public Query saveQuery(Query query) {

        if (queryRepository.findByName(query.getName()).isPresent()) {
            throw new RuntimeException("El nombre de la query ya existe");
        }

        Query querySaved;

        querySaved = queryRepository.save(query);

        for (Comment c : querySaved.getComments()) {
            c.setQuery(querySaved);
        }

        return queryRepository.save(query);
    }


    /**
     * Método encargado de actualizar una query
     *
     * @param query, entidad query a actualizar con los datos actualizados
     * @return La query actualizada en la base de datos
     */
    @Override
    @Transactional
    public Query updateQuery(Query query) {

        Query querySaved = queryRepository.findById(query.getIdquery()).orElse(null);

        if (querySaved != null) {

            querySaved = queryRepository.save(query);

            for (Comment c : querySaved.getComments()) {
                c.setQuery(querySaved);
            }

            return queryRepository.save(querySaved);

        }
        return null;
    }

    /**
     * Método encargado de eliminar una query
     *
     * @param query, entidad a eliminar
     * @return booleano para verificar la eliminación de la query
     */
    @Override
    @Transactional
    public boolean deleteQuery(Query query) {

        Optional<Query> result = queryRepository.findById(query.getIdquery());

        if (result.isPresent()) {
            queryRepository.delete(result.get());
            return true;
        }

        return false;
    }


}
