package com.cipfpmislata.domain.model;

import java.util.Objects;

public class Author {
    private String name;
    private String nationality;
    private String biographyEs;
    private String biographyEn;
    private int birthYear;
    private Integer deathYear;
    private String slug;

    public Author(String name, String nationality, String biographyEs, String biographyEn, int birthYear, Integer deathYear, String slug) {
        this.name = name;
        this.nationality = nationality;
        this.biographyEs = biographyEs;
        this.biographyEn = biographyEn;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.slug = slug;
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

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
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
        Author author = (Author) o;
        return birthYear == author.birthYear && Objects.equals(name, author.name) && Objects.equals(nationality, author.nationality) && Objects.equals(biographyEs, author.biographyEs) && Objects.equals(biographyEn, author.biographyEn) && Objects.equals(deathYear, author.deathYear) && Objects.equals(slug, author.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, biographyEs, biographyEn, birthYear, deathYear, slug);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", biographyEs='" + biographyEs + '\'' +
                ", biographyEn='" + biographyEn + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", slug='" + slug + '\'' +
                '}';
    }
}
