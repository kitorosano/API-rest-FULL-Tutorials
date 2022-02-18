package co.sofka.springbootserver.repositories;

import co.sofka.springbootserver.models.TutorialModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepository extends JpaRepository<TutorialModel, Long> {

    /**
     * Metodo para buscar todos los tutoriales publicados.
     * @param published
     * @return Una lista de todos los objetos tutoriales cuyo estado published sea verdadero
     */
    List<TutorialModel> findByPublished(boolean published);

    /**
     * Metodo para buscar todos los tutoriales por el titulo
     * @param title
     * @return Una lista de todos los objetos tutoriales cuyos titulos coincidan.
     */
    List<TutorialModel> findByTitleContaining(String title);
}
