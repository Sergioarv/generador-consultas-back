package co.com.sergio.generadorconsultas.repository;

import co.com.sergio.generadorconsultas.entity.QuerySave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 14/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Repository
public interface QuerySaveRepository extends JpaRepository<QuerySave, Integer> {
}
