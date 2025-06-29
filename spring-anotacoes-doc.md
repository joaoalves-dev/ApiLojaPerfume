# 📘 Documentação Técnica — Anotações Java Spring

## 📌 Índice

1. [@RestControllerAdvice e @ExceptionHandler](#restcontrolleradvice-e-exceptionhandler)  
2. [@Mapper e MapStruct](#mapper-e-mapstruct)  
3. [Anotações para Testes Unitários](#anotações-para-testes-unitários)  
4. [Anotações Lombok](#anotações-lombok)  
5. [Validações: @NotBlank vs @NotNull](#validações-notblank-vs-notnull)  

---

## @RestControllerAdvice e @ExceptionHandler

- **@RestControllerAdvice**:  
  Usada para capturar exceções lançadas por `@RestController` em toda a aplicação. Combina `@ControllerAdvice` com `@ResponseBody`.

- **@ExceptionHandler**:  
  Define métodos que tratam exceções específicas. Pode ser usada isoladamente ou dentro de um `@RestControllerAdvice`.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleRuntime(RuntimeException ex) {
        return new ErroResposta("Erro interno", ex.getMessage());
    }
}
```

---

## @Mapper e MapStruct

- **Sobre `componentModel = "spring"`:**  
  Configura o MapStruct para criar o mapper como um bean gerenciado pelo Spring, permitindo a injeção automática via `@Autowired`.

- **@Mapper**:  
  Anotação da lib [MapStruct](https://mapstruct.org/) para gerar implementações de mapeamento entre classes (DTO <-> Entidade).

- **Por que usar?**
  - Elimina a necessidade de codificar mapeamentos manualmente.
  - Melhora legibilidade e manutenção.
  - Permite utilizar **expressões lógicas** para transformar ou adaptar campos entre classes.

```java
@Mapper(componentModel = "spring")
public interface PerfumeMapper {
    @Mapping(target = "nomeCompleto", expression = "java(perfume.getMarca() + " - " + perfume.getNome())")
    PerfumeDTO toDTO(Perfume perfume);

    Perfume toEntity(PerfumeDTO dto);
}
```

- **Ponto de atenção:**
  - Embora as expressões lógicas (`expression = "java(...)"`) sejam poderosas, seu uso em excesso pode comprometer a **clareza** e **manutenibilidade** do código.
  - Mapeamentos muito complexos dentro da interface geram dificuldades para testes, debugging e futuras alterações. Nestes casos, considere delegar a lógica para métodos auxiliares.

---

## Anotações para Testes Unitários

- **@Test**:
  - Marca um método como um caso de teste.
  - É fornecida pelo JUnit (mais comum o JUnit 5: `org.junit.jupiter.api.Test`).

```java
@Test
void deveCalcularTotalComDesconto() {
    // lógica do teste aqui
}
```

- **@BeforeEach / @AfterEach**:
  - `@BeforeEach`: executa um método **antes de cada teste**.
  - `@AfterEach`: executa um método **após cada teste**.
  - Utilizadas para inicializar ou limpar dados entre os testes.

```java
@BeforeEach
void inicializar() {
    carrinho = new Carrinho();
}

@AfterEach
void finalizar() {
    carrinho.limpar();
}
```

- **@Mock / @InjectMocks** (usadas com Mockito):
  - `@Mock`: cria objetos simulados de dependências (fakes).
  - `@InjectMocks`: injeta os mocks criados nas dependências reais da classe que está sendo testada.

```java
@Mock
private ProdutoService produtoService;

@InjectMocks
private PedidoService pedidoService;
```

- **@SpringBootTest**:
  - Usada para testes de integração que precisam do **contexto completo da aplicação Spring**.
  - Permite injeção de beans reais e simula a execução da aplicação como no ambiente produtivo.

```java
@SpringBootTest
class PedidoServiceIT {

    @Autowired
    private PedidoService pedidoService;

    @Test
    void deveSalvarPedidoComSucesso() {
        // teste de integração
    }
}
```

---

## Anotações Lombok

- **@Data**:  
  Gera `getters`, `setters`, `toString`, `equals` e `hashCode`.  
  - **Ponto negativo**: pode gerar métodos que você não precisa, o que pode afetar a performance ou expor informações sensíveis (ex: `toString` com senhas).  
  - Também pode causar conflitos em cenários mais complexos, como herança ou quando o controle fino dos métodos é necessário.

- **@Getter / @Setter**:  
  Geram métodos individualmente.

- **@Builder**:  
  Implementa o padrão builder.

- **@AllArgsConstructor / @NoArgsConstructor**:  
  Construtores com todos ou nenhum argumento.

```java
@Data
@Entity
public class Perfume {
    @Id @GeneratedValue
    private Long id;
    private String nome;
}
```

---

## Validações: @NotBlank vs @NotNull

- **@NotNull**:
  - Garante que o campo **não seja nulo**.
  - Não valida se Strings estão vazias ou em branco.
  - Pode ser usada em qualquer tipo de objeto.

```java
public class Produto {
    @NotNull
    private Double preco;
}
```

- **@NotBlank**:
  - Garante que o campo **não seja nulo, vazio ou composto apenas por espaços em branco**.
  - Específica para `String`.

```java
public class Cliente {
    @NotBlank
    private String nome;
}
```

- **Resumo da diferença**:
  - `@NotNull`: verifica apenas se é diferente de `null`.
  - `@NotBlank`: verifica se é diferente de `null`, `""` e `"   "`.

```java
public record PerfumeDTO(
    @NotBlank String nome,
    @NotNull Double preco
) {}
```
