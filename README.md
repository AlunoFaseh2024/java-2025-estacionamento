# java-2025-estacionamento
Desenvolver o sistema de controle de estacionamentos da faculdade

## ATIVIDADE - SISTEMA DE ESTACIONAMENTO

Vocês foram contratados para desenvolver o sistema de controle de estacionamentos da faculdade.

Para isso, será necessário registrar a placa, modelo e marca do veículo, além do horário de entrada.

O sistema deve permitir consultar as vagas ocupadas, as vagas livres e o total de carros no estacionamento.

Não deve ser possível sobrescrever a vaga, e quando isso acontecer uma mensagem de erro deve ser devolvida nos padões de API.

Utilize os princípios da programação orientada a objetosa partir do projeto inicial de Springboot disponibilizado.
 
API com endpoints de:
inserção de dados do carro
consulta das vagas
Registrar o usuário que está realizando as entradas de dados naquele instante.

Endpoint para dar a saída de um veículo, o sistema deve calcular o total que deve ser pago, a consulta é feita pela placa. A cobrança é feita por hora, onde, carro pequeno paga R$16,00, carro grande R$25,00, e motos R$8,00 e devolver um JSON com todos os valores.

Considerações:
O código fonte deve ser disponibilizado em repositório público no Github.
Todos devem entregar o funcionamento do sistema com todos os requisitos.

## Como fazer funcionar

1 - Crie um banco de dados chamado __estacionamento__ no XAMPP.

2 - Crie uma conta de usuário com nome 'rafael' e senha 'SenhaTeste123', ou também crie com outros dados e informe isso no arquivo _application.properties_.

3 - Rode o arquivo __EstacionamentoApplication.java__ em _java-2025-estacionamento\src\main\java\com\cleberleao\estacionamento_ .

4 - Abra o navegador em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) .

## Como usar

Esses passos devem ser feito em ordem, pois, o passo 3 só pode ser feito depois que os passos 1 e o 2 forem executados.

1 - Crie um usuário no _POST/usuarios_. Informe o nome e e-mail.

2 - Vá em _POST/vagas_ e defina a quantidade total de vagas no estacionamento.

3 - Vá em _POST/veiculos_ e registre a entrada de um veículo.