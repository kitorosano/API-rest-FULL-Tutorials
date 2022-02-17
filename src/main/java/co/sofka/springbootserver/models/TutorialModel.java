package co.sofka.springbootserver.models;

import javax.persistence.*;

@Entity
public class TutorialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "published", nullable = false)
    private boolean published;

    @Column(name = "price", nullable = false)
    private float price;

    public TutorialModel() {
    }

    public TutorialModel(String title, String description, boolean published, float price) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.price = price; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public float getPrice() {
      return price;
    }

    public void setPrice(float price) {
      this.price = price;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + ", price=" + price + "]";
    }
}
