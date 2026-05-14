package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // Não esqueça de importar a Lista!
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao seu App de Terapias!");
        return "index";
    }

    // NOVA ROTA PARA TERAPIAS
    @GetMapping("/terapias")
    public String terapias(Model model) {
        // Criando uma lista de objetos usando nossa classe POO
        List<OleoEssencial> listaDeOleos = List.of(
            new OleoEssencial("Lavanda", "Acalma a mente, reduz ansiedade e ajuda a dormir."),
            new OleoEssencial("Alecrim", "Melhora o foco, a memória e dá energia."),
            new OleoEssencial("Hortelã-Pimenta", "Alivia dores de cabeça e desobstrui vias respiratórias.")
        );

        // Enviando a lista para o HTML com o nome de "oleos"
        model.addAttribute("oleos", listaDeOleos);
        
        return "terapias"; // O Spring vai procurar um arquivo terapias.html
    }

        @GetMapping("/consulta-avaliativa")
        public String consultaAvaliativa(Model model) {
        return "consulta-avaliativa"; // Nome do arquivo HTML que vamos criar
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

// Recebe o clique do botão "Entrar"
    @PostMapping("/login")
    public String efetuarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            // A MÁGICA AQUI: Salva o usuário na sessão do navegador
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/"; // Volta para a home agora como logado
        } else {
            model.addAttribute("erro", "E-mail ou senha incorretos!");
            return "login";
        }
    }

    // Criar a rota para SAIR do sistema
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Limpa a sessão (esquece o usuário)
        return "redirect:/";
    }

    // Mostra a tela vazia e envia um objeto "Usuario" em branco para o HTML preencher
    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Recebe os dados preenchidos quando o cliente clica em "Cadastrar"
    @PostMapping("/registro")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        // Salva no banco de dados!
        usuarioRepository.save(usuario);
        
        // Redireciona o cliente para a tela de login para ele entrar
        return "redirect:/login";
    }

    // === ÁREA DO ADMINISTRADOR ===
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        // Busca TODOS os usuários salvos no banco de dados
        model.addAttribute("listaUsuarios", usuarioRepository.findAll());
        
        // Manda abrir uma página chamada usuarios.html
        return "usuarios";
    }
}