# API Rest Reservas ( Backend )

Este projeto é uma API REST, desenvolvida com Java Spring Boot, que permite gerenciar reservas de uma Casa de Temporada. A aplicação possibilita o agendamento de estadias em datas específicas, com informações do hóspede e quantidade de pessoas.

## Tecnologias Utilizadas

-   Java 17
-   Spring Boot
-   Spring Data JPA
-   Banco de dados H2 (in-memory database)
-   Mockito (para testes)

## Como Executar

1. Clone o repositório do projeto em sua máquina local usando o comando abaixo.

```bash
    git clone https://github.com/Major2571/reservas.git
```
2. Vá até o diretorio do projeto: 
3.  Certifique-se de ter o Java Spring Boot e o H2 Database configurados.
4.  Execute a aplicação.


## Endpoints da API
A API possui os seguintes endpoints:

### Criar uma Reserva:
**Método**: POST      
**Endpoint**: /reservas    

**Corpo da solicitação (JSON):**
```bash
{
	"nomeHospede": "Fulano de Tal",
	"dataInicio": "2023-08-10",
	"dataFim": "2023-08-15",
	"quantidadePessoas": 4
}
```

**Resposta (JSON):**
```bash
{
	"id": 1,
	"nomeHospede": "Fulano de Tal",
	"dataInicio": "2023-08-10",
	"dataFim": "2023-08-15",
	"quantidadePessoas": 4,
	"status": "CONFIRMADA"
}
```

### Obter todas as reservas:
**Método:** GET  
**Endpoint:** /reservas  

**Resposta (JSON):**  
```bash
[ 
	{ 
		"id": 1, 
		"nomeHospede": "Fulano de Tal", 
		"dataInicio": "2023-08-10", 
		"dataFim": "2023-08-15", 
		"quantidadePessoas": 4, 
		"status": "CONFIRMADA" 
	}, 
	{ 
		"id": 2, 
		"nomeHospede": "Ciclano da Silva", 
		"dataInicio": "2023-09-01", 
		"dataFim": "2023-09-05", 
		"quantidadePessoas": 2, 
		"status": "PENDENTE" 
	}
]
```

### Obter uma reserva específica por ID:
**Método:** GET  
**Endpoint:** /reservas/{id}  

**Resposta (JSON):**
```bash
{
	"id": 1,
	"nomeHospede": "Fulano de Tal",
	"dataInicio": "2023-08-10",
	"dataFim": "2023-08-15",
	"quantidadePessoas": 4,
	"status": "CONFIRMADA"
}
```

### Atualizar uma reserva existente:
**Método:** PUT  
**Endpoint:** /reservas/{id}  

**Corpo da solicitação (JSON):**  
```bash
{
	"nomeHospede": "Fulano da Silva",
	"dataInicio": "2023-08-12",
	"dataFim": "2023-08-17",
	"quantidadePessoas": 5,
	"status": "PENDENTE"
}
```
**Resposta (JSON):**
```bash
{
	"id": 1,
	"nomeHospede": "Fulano da Silva",
	"dataInicio": "2023-08-12",
	"dataFim": "2023-08-17",
	"quantidadePessoas": 5,
	"status": "PENDENTE"
}
```

### Cancelar uma reserva:
**Método:** DELETE  
**Endpoint:** /reservas/{id}/cancelar

**Resposta (JSON):**  
```bash
{
	"id": 1,
	"nomeHospede": "Fulano da Silva",
	"dataInicio": "2023-08-12",
	"dataFim": "2023-08-17",
	"quantidadePessoas": 5,
	"status": "CANCELADA"
}
```
