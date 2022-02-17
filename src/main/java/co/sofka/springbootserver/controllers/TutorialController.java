package co.sofka.springbootserver.controllers;

import co.sofka.springbootserver.models.TutorialModel;
import co.sofka.springbootserver.repositories.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
  
  @Autowired
  TutorialRepository tutorialRepository;
  
  // ***** CREATE ***** //
  
  /**
   * Metodo para crear un nuevo objeto Tutorial, utilizando el modelo del body del request.
   * @param tutorial
   * @return Un response exitoroso con el nuevo objeto creado, o un response fallido.
   */
  @PostMapping("/tutorials")
  public ResponseEntity<TutorialModel> createTutorial(@RequestBody TutorialModel tutorial){
    try {
      TutorialModel _tutorial = tutorialRepository.save(new TutorialModel(tutorial.getTitle(), tutorial.getDescription(), false, tutorial.getPrice()));
      return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  
  // ***** READ ***** //

  /**
   * Metodo para obtener todos los objetos Tutorial, utilizando el parametro title opcionalmente, para filtrar por titulo
   * @param title
   * @return Un response exitoroso con la lista de todos tutoriales si no esta vacio, o todos aquellos filtrados por titulo, o un response vacio. 
   */
  @GetMapping("/tutorials")
  public ResponseEntity<List<TutorialModel>> getAllTutorials(@RequestParam(required = false) String title){
    try {
      List<TutorialModel> tutorials = new ArrayList<>();
      
      if(title == null)
        tutorialRepository.findAll().forEach(tutorials::add);
      else
        tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
      
      if(tutorials.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

   /**
   * Metodo para obtener un objeto Tutorial mediante su id
   * @param id
   * @return Un response exitoroso con el tutorial, o un response vacio por no haber encontrado el tutorial con el id.
   */
  @GetMapping("/tutorials/{id}")
  public ResponseEntity<TutorialModel> getTutorialById(@PathVariable("id") long id) {
    Optional<TutorialModel> tutorialData = tutorialRepository.findById(id);
    
    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  /**
   * Metodo para obtener todos los objetos Tutorial cuyo estado published sea verdadero
   * @return Un response exitoroso con la lista de todos tutoriales, filtrados por el estado verdadero de published, o un response fallido. 
   */
  @GetMapping("/tutorials/published")
  public ResponseEntity<List<TutorialModel>> findByPublished() {
    try {
      List<TutorialModel> tutorials = tutorialRepository.findByPublished(true);
      
      if (tutorials.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  /**
   * Metodo para obtener el precio Tutorial mediante su id
   * @param id
   * @return Un response exitoroso con el precio de un tutorial, o un response vacio por no haber encontrado el tutorial con el id.
   */
  @GetMapping("/tutorials/{id}/price")
  public ResponseEntity<Float> getTutorialPriceById(@PathVariable("id") long id) {
    Optional<TutorialModel> tutorialData = tutorialRepository.findById(id);
    
    if (tutorialData.isPresent()) {
      return new ResponseEntity<>(tutorialData.get().getPrice(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  
  // ***** UPDATE ***** //
  
  /**
   * Metodo para modificar un objeto tutorial mediante su id
   * @param id
   * @param tutorial
   * @return Un response exitoso con el tutorial modificado, o un response vacio por no haber encontrado el tutorial con el id.
   */
  @PutMapping("/tutorials/{id}")
  public ResponseEntity<TutorialModel> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialModel tutorial){
    Optional<TutorialModel> tutorialData = tutorialRepository.findById(id);
    
    if(tutorialData.isPresent()){
      TutorialModel _tutorial = tutorialData.get();
      _tutorial.setTitle(tutorial.getTitle());
      _tutorial.setDescription(tutorial.getDescription());
      _tutorial.setPublished(tutorial.isPublished());
      _tutorial.setPrice(tutorial.getPrice());
      return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    } else
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  /**
   * Metodo para modificar los objetos tutoriales mediante su titulo
   * @param title
   * @param tutorial
   * @return Un response exitoso con la lista de tutoriales modificados, o un response vacio por no haber encontrado tutoriales con el titulo.
   */
  @PutMapping("/tutorials/{title}")
  public ResponseEntity<List<TutorialModel>> updateTutorial(@PathVariable("title") String title, @RequestBody TutorialModel tutorial){
    List<TutorialModel> tutorialData = tutorialRepository.findByTitleContaining(title);
    List<TutorialModel> updatedTutorials = new ArrayList<TutorialModel>();
    
    if(tutorialData.size() > 0){
      tutorialData.forEach((_tutorial) -> {
        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());
        _tutorial.setPrice(tutorial.getPrice());
        updatedTutorials.add(tutorialRepository.save(_tutorial));
      });
      return new ResponseEntity<>(updatedTutorials, HttpStatus.OK);
    } else
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  
  // ***** DELETE ***** //
  
  /**
   * Metodo para eliminar un tutorial mediante su id
   * @param id
   * @return Un response exitoso con un mensaje si se ha eliminado correctamente, o un response fallido.
   */
  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
    try {
      tutorialRepository.deleteById(id);
      return new ResponseEntity<>("Tutorials DELETE!! ",HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  /**
   * Metodo para eliminar tutoriales mediante su titulo
   * @param title
   * @return Un response exitoso con un mensaje si se han eliminado correctamente, o un response fallido.
   */
  @DeleteMapping("/tutorial/{title}")
  public ResponseEntity<String> deleteTutorial(@PathVariable("title") String title) {
    try {
      tutorialRepository.deleteByTitle(title);
      return new ResponseEntity<>("Tutorial DELETE!! ",HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  /**
   * Metodo para eliminar todos los tutoriales
   * @param title
   * @return Un response exitoso con un mensaje si se han eliminado correctamente, o un response fallido.
   */
  @DeleteMapping("/tutorials")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    try {
      tutorialRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
}
