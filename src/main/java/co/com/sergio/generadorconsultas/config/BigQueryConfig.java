package co.com.sergio.generadorconsultas.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Author: Ing Sergio Abelardo Rodríguez Vásquez
 * Date: 10/11/2023
 * Email: ingsergiorodriguezv@gmail.com
 **/

@Configuration
public class BigQueryConfig {

    /**
     * Configura y devuelve un bean de BigQuery.
     * Se utiliza el archivo "credentials.json" para obtener las credenciales de Google Cloud.
     *
     * @return Instancia de BigQuery configurada.
     * @throws IOException, Si hay un error al leer las credenciales desde el archivo.
     */
    @Bean
    public BigQuery bigQuery() throws IOException {

        ClassPathResource resource = new ClassPathResource("credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        return BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
