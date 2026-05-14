package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Isso avisa ao Spring: "Crie uma tabela no banco de dados para esta classe"
public class Usuario {

    @Id // Diz que este é o identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco vai gerar o ID automaticamente (1, 2, 3...)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    // Construtor vazio (obrigatório para o Spring)
    public Usuario() {
    }

    // Abaixo estão os "Getters e Setters" para o Java conseguir ler e gravar os dados
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}