# 🚨 EmergencyTracker

**EmergencyTracker** é uma aplicação desenvolvida em **Spring Boot** para gerenciar alertas de emergência baseados em localização. Dispositivos (sensores, centrais, celulares, etc.) podem enviar sinais de perigo (como chuva intensa, enchente), gerando alertas automáticos que são distribuídos a usuários próximos, promovendo uma resposta rápida e coordenada.

---

## 👨‍💻 Desenvolvedores

- **Carolina Estevam Rodgerio** (RA: 554975)  
- **Enrico Andrade D'Amico** (RA: 557706)  
- **Lucas Thalles Dos Santos** (RA: 558886)  

---

## 🔧 Tecnologias Utilizadas

- **Spring Boot** – Framework para aplicações Java robustas  
- **Spring Data JPA** – Persistência com Hibernate  
- **Spring Security** – Autenticação/Autorização com roles (ADMIN, USER)  
- **Spring Transaction** – Controle de transações  
- **Jackson** – JSON (serialização/deserialização)  
- **Lombok** – Redução de código boilerplate  
- **Database** (compatível com MySQL/Oracle)  
- **Maven** – Gerenciamento de dependências  
- **Swagger** – Testes e documentação de APIs  

---

## 🧠 Modelo de Negócio

### 👤 Usuários
- Login e senha com autenticação via Spring Security
- Associados a dispositivos
- Recebem alertas por geolocalização
- Podem criar **solicitações de ajuda**

### 📱 Dispositivos
- Tipos: **RUNA**, **CENTRAL**, **SENSOR**
- Enviam sinais e recebem alertas
- Associados a usuários com localização geográfica

### 📶 Sinais
- Informações captadas por dispositivos (chuva, vento, etc.)
- Incluem tipo, valor, unidade e localização

### 🚨 Alertas
- Gerados pelo admin.
- Enviados a usuários próximos
- Possuem severidade e status

### 🆘 Solicitações de Ajuda
- Criadas por usuários
- Tipos como: **resgate**, **saúde**, **abrigo**
- Priorizadas por nível de urgência

---

## 🔁 Fluxo do Sistema

1. Um **dispositivo** envia um **sinal** (ex.: chuva forte).
2. O sistema gera um **alerta** com base no sinal e localização.
3. O alerta é enviado a **usuários próximos**.
4. Usuários visualizam alertas e podem abrir **solicitações de ajuda**.
5. Administradores gerenciam todos os dados da aplicação.

---

## 🗄️ Estrutura do Banco de Dados

### 🧑‍💼 `USUARIO`
- ID, Nome, CPF, Telefone, Email
- Timestamps de criação e atualização
- **Relacionamentos**:
  - `OneToMany` com `DISPOSITIVO` e `SOLICITACAO_AJUDA`
  - `ManyToMany` com `ALERTA`

### 🔑 `LOGIN`
- ID, Login, Senha
- Relacionado a `USUARIO` e `TIPO_LOGIN` (ADMIN/USER)

### 📱 `DISPOSITIVO`
- Tipo (`RUNA`, `SENSOR`, `CENTRAL`), Status, Localização
- Relacionado a `USUARIO` e `SINAL`

### 🌍 `LOCALIZACAO`
- Latitude, Longitude, Descrição

### 📶 `SINAL`
- Tipo, Valor, Unidade, Data/Hora
- Relacionado a `LOCALIZACAO` e `DISPOSITIVO`

### 🚨 `ALERTA`
- Tipo, Conteúdo, Severidade, Status
- Relacionado a `SINAL`, `LOCALIZACAO` e `USUARIO`

### 🆘 `SOLICITACAO_AJUDA`
- Tipo, Conteúdo, Nível de prioridade
- Relacionado a `USUARIO`

### 🔗 Tabelas Auxiliares
- `SINAL_DISPOSITIVO`: Relacionamento `SINAL <-> DISPOSITIVO`
- `ALERTA_USUARIO`: Relacionamento `ALERTA <-> USUARIO`

---

## 🧩 Funcionalidades

- 🔐 **Autenticação de Usuários** (JWT + Roles)
- 📲 **CRUD de Dispositivos**
- 📡 **Envio de Sinais** com localização
- 🚨 **Geração de Alertas**
- 🆘 **Solicitação de Ajuda** por parte dos usuários
- 📈 **Consultas e Filtros** (ex.: alertas por usuário)


---

## ▶️ Como Executar

### ✅ Pré-requisitos

- Java 17+
- Maven
- Oracle Database (XE ou 19c)  
  *(ou adaptável para MySQL/PostgreSQL)*
- Navegador com acesso ao Swagger

### 🛠️ Configuração Oracle

```sql
-- Conecte-se como SYSTEM:
sqlplus system/oracle@localhost:1521/XE
você pode conectar através do Oracle SQL Developer
acessar a conexao de usuário adminstrador 'SYSTEM'

-- Crie o usuário/schema:

CREATE TABLESPACE tracker
LOGGING DATAFILE 'tracker.dbf'
SIZE 100m AUTOEXTEND ON NEXT 100m EXTENT MANAGEMENT LOCAL;

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER tracker
IDENTIFIED BY tracker
DEFAULT TABLESPACE tracker
QUOTA UNLIMITED ON tracker;

GRANT CONNECT TO tracker;
GRANT RESOURCE TO tracker;
GRANT CREATE SESSION TO tracker;
GRANT UNLIMITED TABLESPACE TO tracker;

## Passos para rodar

# Clone o repositório
git clone https://github.com/Enrico-AD/EmergencyTracker.git
cd EmergencyTracker
#Configure o application-dev.yml

  datasource:
    url: jdbc:oracle:thin:@localhost:1521:XE #XE
    username: tracker
    password: tracker

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run

-- Acesse o Swagger:
📍 http://localhost:8080/emergencytracker/swagger-ui.html
