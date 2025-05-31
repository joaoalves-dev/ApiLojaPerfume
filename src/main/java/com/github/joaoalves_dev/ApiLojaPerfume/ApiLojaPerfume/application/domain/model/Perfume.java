package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String marca;
    private String genero;
    private Double volume;
    private Double preco;

    //MINHA IDE NAO RECONHECE O LOMBOK

    public Perfume() {
    }

    public Perfume(String id, String nome, String marca, String genero, Double volume, Double preco) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.genero = genero;
        this.volume = volume;
        this.preco = preco;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
