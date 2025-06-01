package com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.model;

import com.github.joaoalves_dev.ApiLojaPerfume.ApiLojaPerfume.application.domain.enums.FuncionarioCargo;
import jakarta.persistence.*;

@Entity
public class Funcionario {

    @Id
    private String cpf;
    private String nome;
    @Enumerated(EnumType.STRING)
    private FuncionarioCargo cargo;
    private String email;

    //MINHA IDE ESTA COM ERRO LOMBOK

    public Funcionario(){}

    public Funcionario(String cpf, String email, String nome, FuncionarioCargo cargo) {
        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
        this.cargo = cargo;
    }

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

    public FuncionarioCargo getCargo() {
        return cargo;
    }

    public void setCargo(FuncionarioCargo cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}