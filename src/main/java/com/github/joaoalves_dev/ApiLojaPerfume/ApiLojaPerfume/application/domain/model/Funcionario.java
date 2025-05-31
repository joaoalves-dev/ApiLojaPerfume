package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario {

    @Id
    private String cpf;
    private String nome;
    private String cargo;
    private String email;

    public Funcionario() { }

    public Funcionario(String cpf, String nome, String cargo, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
    }
    //MINHA IDE NAO RECONHECE O LOMBOK

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}