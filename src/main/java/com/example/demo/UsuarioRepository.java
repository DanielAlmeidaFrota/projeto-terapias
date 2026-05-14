package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// O JpaRepository já vem com comandos prontos como "salvar", "deletar", "buscarTodos"
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Comando customizado que usaremos no login para procurar se o e-mail existe
    Usuario findByEmail(String email);
}