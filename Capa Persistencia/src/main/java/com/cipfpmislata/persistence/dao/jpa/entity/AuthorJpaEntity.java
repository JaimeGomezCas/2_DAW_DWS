package com.cipfpmislata.persistence.dao.jpa.entity;


import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "author")
public class AuthorJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String nationality;
    @Column(name = "biography_es")
    private String biographyEs;
    @Column(name = "biography_en")
    private String biographyEn;
    @Column(name = "birth_year")
    private int birthYear;
    @Column(name = "death_year")
    private int deathYear;
    private String slug;
    @OneToMany(mappedBy = "author")
    private List<BookJpaEntity> bookAuthors;

    public AuthorJpaEntity(Long id, String name, String nationality, String biographyEs, String biographyEn, Integer birthYear, Integer deathYear, String slug) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.biographyEs = biographyEs;
        this.biographyEn = biographyEn;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.slug = slug;
    }

    public AuthorJpaEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBiographyEs() {
        return biographyEs;
    }

    public void setBiographyEs(String biographyEs) {
        this.biographyEs = biographyEs;
    }

    public String getBiographyEn() {
        return biographyEn;
    }

    public void setBiographyEn(String biographyEn) {
        this.biographyEn = biographyEn;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorJpaEntity that = (AuthorJpaEntity) o;
        return id == that.id && birthYear == that.birthYear && deathYear == that.deathYear && Objects.equals(name, that.name) && Objects.equals(nationality, that.nationality) && Objects.equals(biographyEs, that.biographyEs) && Objects.equals(biographyEn, that.biographyEn) && Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nationality, biographyEs, biographyEn, birthYear, deathYear, slug);
    }

    @Override
    public String toString() {
        return "AuthorJpaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", biographyEs='" + biographyEs + '\'' +
                ", biographyEn='" + biographyEn + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", slug='" + slug + '\'' +
                '}';
    }
}
