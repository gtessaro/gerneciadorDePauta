# gerneciadorDePauta

API de serviços de votação orientado pela abertura de uma pauta para ser votada durante um determinado período, onde somente pode haver um voto por associado.

 Objetivos

  - Cadastrar uma nova pauta;
  - Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
  - Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
  - Contabilizar os votos e dar o resultado da votação na pauta.
 
# Tecnologias 
* Java 8
* Spring Boot Web; JPA;
* Hibernate
* Swagger 2
* MySql
  * Para utilizar o mysql, é necessário configurar a senha e o usuário no arquivo application.properties
* H2 Database 
  * Em ambiente de desenvolvimento foi utilizado o banco de dados em memória para facilitar a criação das tabelas

# Funcionamento do Sistema

## Cadastrar Pautas
  * Cada pauta precisa ter um título e uma descrição (por questão de simplificar o desenvolvimento, os campos não são obrigatórios).
  * É possível listar todas as pautas ou filtrar por status
  * As pautas cadastradas ficam com status "AGUARDANDO"
  * Pautas podem ser canceladas ou encerradas
    * Ao encerrar uma pauta, os votos serão computados e a situação da pauta será definida como APROVADA ou NAO_APROVADA
    * Para uma pauta ser aprovada, é necessário ter a maioria absoluta de votos sim, caso de empata, a pauta não será aprovada.
    * Como os valores de votos não mudam ao encerrar a pauta, eles serão persistidos na pauta mesmo, para facilitar a consulta de quantidade de votos.  
  
## Criar Sessões
  * Para iniciar uma sessão, a pauta tem que estar com Status aguardando
    * Ao abrir uma sessão, a Pauta fica como aberta para permitir votação
    * A sessao fica aberta por um tempo escolhido ou por 1 minuto por padrão
    * Ao iniciar uma sessão, o status da Pauta é alterado para ABERTA (permitindo votos)
    * Após o tempo de duração da sessão, o status da pauta volta para AGUARDANDO 
    
## Votos
  * Para votar é necessário informar o cpf do associado e o id da pauta
  * Só é permitido a votação em Pautas abertas
  * Caso o associado esteja permitido votar, o sistema computará o voto
  
  
## Swagger
```
http://localhost:8080/swagger-ui.html/
```
## Versionamento
* O versionamento da API é feito através do endpoint:
  * tendo o /v1 no endpoint significa que é a primeira versão
  * caso seja criada uma nova versão, o endpoint utilizado iria conter /v2 e assim por diante. 
  
