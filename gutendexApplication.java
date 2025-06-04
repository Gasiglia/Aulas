package com.exemplo.gutendex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.persistence.*;


import java.util.*;

@SpringBootApplication
public class GutendexApplication {
    public static void main(String[] args) {
        SpringApplication.run(GutendexApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@Entity
class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}
    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public String getNome() { return nome; }
    public Integer getAnoNascimento() { return anoNascimento; }
    public Integer getAnoFalecimento() { return anoFalecimento; }
}

@Entity
class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idiomas;
    private Integer numeroDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setIdiomas(String idiomas) { this.idiomas = idiomas; }
    public void setNumeroDownloads(Integer numeroDownloads) { this.numeroDownloads = numeroDownloads; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public String getIdiomas() { return idiomas; }
}

interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer ano1, Integer ano2);
}

interface LivroRepository extends JpaRepository<Livro, Long> {
    Long countByIdiomas(String idiomas);
}

@Service
class GutendexService {
    @Autowired
    private RestTemplate restTemplate;

    public List<LivroDTO> buscarLivrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;
        ResponseGutendexDTO resposta = restTemplate.getForObject(url, ResponseGutendexDTO.class);
        return resposta != null ? resposta.getResults() : List.of();
    }
}

@Component
class MeuRunner implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;
    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private AutorRepository autorRepo;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o título do livro a buscar:");
        String titulo = sc.nextLine();

        List<LivroDTO> livros = gutendexService.buscarLivrosPorTitulo(titulo);

        livros.forEach(dto -> {
            Autor autor = autorRepo.findByNome(dto.getAutorNome())
                    .orElseGet(() -> autorRepo.save(new Autor(dto.getAutorNome(), dto.getAnoNascimento(), dto.getAnoFalecimento())));

            Livro livro = new Livro();
            livro.setTitulo(dto.getTitulo());
            livro.setIdiomas(dto.getIdiomas());
            livro.setNumeroDownloads(dto.getNumeroDownloads());
            livro.setAutor(autor);

            livroRepo.save(livro);
        });

        System.out.println("Livros salvos no banco de dados.");

        System.out.println("Digite um idioma para estatísticas (ex: en, pt):");
        String idioma = sc.nextLine();
        Long qtd = livroRepo.countByIdiomas(idioma);
        System.out.println("Quantidade de livros no idioma " + idioma + ": " + qtd);

        System.out.println("Digite um ano para ver autores vivos:");
        int ano = sc.nextInt();
        List<Autor> vivos = autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
        vivos.forEach(a -> System.out.println(a.getNome()));
    }
}

// DTOs
class LivroDTO {
    private String titulo;
    private List<String> languages;
    private int download_count;
    private List<AutorDTO> authors;

    public String getTitulo() { return titulo; }
    public String getIdiomas() { return String.join(",", languages); }
    public Integer getNumeroDownloads() { return download_count; }
    public String getAutorNome() { return authors.isEmpty() ? "Desconhecido" : authors.get(0).getName(); }
    public Integer getAnoNascimento() { return authors.isEmpty() ? null : authors.get(0).getBirth_year(); }
    public Integer getAnoFalecimento() { return authors.isEmpty() ? null : authors.get(0).getDeath_year(); }
}

class AutorDTO {
    private String name;
    private Integer birth_year;
    private Integer death_year;

    public String getName() { return name; }
    public Integer getBirth_year() { return birth_year; }
    public Integer getDeath_year() { return death_year; }
}

class ResponseGutendexDTO {
    private List<LivroDTO> results;
    public List<LivroDTO> getResults() { return results; }
}
