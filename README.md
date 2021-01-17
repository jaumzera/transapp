# TransApp

## Java

Foi utilizado Java 8 porque ainda (em 13/01/2021) é a versão mais utilizada do Java.

## Sobre o empacotamento
 
O [_packaging by feature_](http://www.javapractices.com/topic/TopicAction.do?Id=205#:~:text=Package%2Dby%2Dfeature%20uses%20packages,with%20minimal%20coupling%20between%20packages) é encorajado pela documentação do Spring e pelo livro Clean Architecture.

O maior benefício desse tipo de empacotamento é poder restringir as classes somente ao seu pacote.
Por exemplo, só faz sentido acessar o `AccountRepository` de dentro do pacote 
`br.com.joaomassan.transapp.account`

Boas referências para esse tipo de empacotamento são a API __Colletions__ e __Persistence__ do Java.

## Sobre o code-styling 

### Formatação

Usado o [google-java-format] (https://google.github.io/styleguide/javaguide.html) como padrão de 
formatação do código.


### Anotações

Anotações são separadas por pacote/framework ordenadas de forma crescente:

```java
@fw1_Annotation1
@fw1_Annotation2UmPoucoMaior
@fw2_Annotation1
@fw2_Annotation2UmPoucoMaior
@fw2_Annotation2UmPoucoMaiorAinda
public class AnnotatedClass { 
  //...
}
```

Usa-se o valor default para tudo. Anotações só são empregadas quando são realmente
necessárias como, por exemplo, para adequar o estilo _cammelCase_ dos atributos do
código com o estilo _snake_case_ do banco de dados.

Exemplo:

```
Java: userId
DB: user_id
``` 

## Lombok

Lombok foi empregado para reduzir a verbosidade do código.

## Divisão dos testes

Os testes foram divididos em duas categorias identificadas pelo sufixo da classe:

* *xxxTest*: indicam testes de unidade
* *xxxIT*: indicam testes de integração

Por integração pode-se entender testes que precisam levantar o contexto do Spring 
para serem executados.

## Banco de dados

* O app usa o H2 em memória tanto para testes quanto para execução.
* O schema é gerado automaticamente pelo JPA a partir das entidades.
* O arquivo `/resources/data.sql` carrega as informações iniciais do projeto no banco.

## Execução

Para executar com o Docker:

```bash
docker build -t jaumzera/transapp .
docker run -p 8080:8080 jaumzera/transapp
```

O app subirá em http://localhost:8080.




