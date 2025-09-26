package es.cesguiro.model;

public class Publisher {

    private long id;
    private String name;
    private String slug;

    public Publisher(String name, String slug, long id) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
