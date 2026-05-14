package com.example.demo; // Atenção: confira se o nome do package é esse mesmo no seu projeto!

public class OleoEssencial {
    private String nome;
    private String beneficio;

    // Construtor
    public OleoEssencial(String nome, String beneficio) {
        this.nome = nome;
        this.beneficio = beneficio;
    }

    // Getters (O Spring e o Thymeleaf precisam dos Getters para ler os dados!)
    public String getNome() {
        return nome;
    }

    public String getBeneficio() {
        return beneficio;
    }
}