
# Controle de Despesas

Um pequeno sistema com operações CRUD para o controle de despesas do usuário.




## Métodos utilizados para realizar as operações.

- Create: Utilizado o método save para inserir uma nova despesa.

```java
   @Override
    public Despesa save(Despesa despesa)
```

- Read: Listar todas as despesas, ou por ID ou por Categoria.

```java
   @Override
    public List<Despesa> findAll()
```

```java
 @Override
    public Optional<Despesa> findById(Long id)
```
```java
  @Override
    public List<Despesa> findByCategoria(Categoria categoria)
```

- Update: Realizar a atualização nos campos de uma despesa já existente.

```java
  @Override
    public Despesa update(Despesa despesa)
```

- Delete: Remover uma despesa a partir de um ID informado pelo usuário.

```java
   @Override
    public void delete(Long id)
```


## Tecnologias Utilizadas
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)  
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)  
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)


## Aprendizados

Durante este projeto, tive a oportunidade de trabalhar com o Maven, o que me permitiu compreender melhor o fluxo de um projeto que utiliza esse framework altamente escalável.

Além disso, pratiquei e aprofundei meus conhecimentos em Orientação a Objetos, especialmente no uso de Interfaces e Enums, que foram fundamentais para a categorização e a estruturação do código, contribuindo para uma escrita mais clara e um entendimento mais eficiente.

A experiência com o JDBC foi igualmente enriquecedora. Foi fascinante observar como a inclusão da dependência no Maven simplifica o processo, permitindo-me criar uma classe de conexão (ConnectionFactory) de forma prática e organizada.

Ademais, trabalhar com o PostgreSQL também foi uma experiência fantástica, uma vez que foi possível compreender a escalabilidade do SGBD.


## Autores

- [@lucasgiavaroti](https://www.github.com/lucasgiavaroti)

