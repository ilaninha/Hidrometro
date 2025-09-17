## SHA 2.0

# Simulador de Hidrômetro Analógico

![Java](https://img.shields.io/badge/Java-17-blue.svg) ![Maven](https://img.shields.io/badge/Maven-3.8-red.svg) ![JavaFX](https://img.shields.io/badge/JavaFX-17-orange.svg) ![Javalin](https://img.shields.io/badge/Javalin-5.6-brightgreen.svg)

> Software para simular o funcionamento de um medidor de água analógico. [cite_start]O propósito principal é fornecer uma interface visual e uma API REST para que outros softwares possam consumir dados de medição sem depender de um hardware real[cite: 4819].

Este projeto foi desenvolvido como uma solução robusta para simular as diversas condições de operação de um hidrômetro, incluindo fluxo normal, falta de água e passagem de ar.

![Screenshot do Simulador](image_c6b589.png)

## ✨ Funcionalidades

* [cite_start]**Interface Gráfica Realista:** Uma UI construída com JavaFX que simula visualmente um hidrômetro, com ponteiros animados e um contador digital[cite: 149].
* [cite_start]**Simulação de Estados:** O comportamento do hidrômetro muda dinamicamente para refletir diferentes condições[cite: 21]:
    * `EstadoComAgua`: Fluxo de água normal, registrando consumo.
    * `EstadoSemAgua`: Interrupção no fornecimento, sem consumo.
    * [cite_start]`EstadoComAr`: Passagem de ar pela tubulação, registrando consumo incorretamente (um cenário do mundo real)[cite: 32].
* [cite_start]**Eventos Aleatórios:** A simulação inclui eventos estocásticos, como a chance de ocorrer uma "falta de água" para aumentar o realismo[cite: 127].
* [cite_start]**Flutuação de Pressão:** A pressão da água não é estática, mas flutua de forma realista em torno de um valor base, utilizando uma distribuição Gaussiana[cite: 115, 121].
* [cite_start]**API REST para Integração:** Expõe os dados da simulação em tempo real através de endpoints REST, permitindo que sistemas externos monitorem o hidrômetro[cite: 58].

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Build e Gerenciamento de Dependências:** Apache Maven
* [cite_start]**Interface Gráfica (GUI):** JavaFX [cite: 150]
* [cite_start]**Servidor da API REST:** Javalin (um framework web leve para Java/Kotlin) [cite: 61]

## 🏛️ Arquitetura

[cite_start]O projeto foi estruturado com base em princípios de design SOLID e padrões de projeto para garantir um código desacoplado, manutenível e extensível[cite: 3, 6].

* [cite_start]**Padrão de Projeto State (Estado):** Utilizado para gerenciar o comportamento complexo do hidrômetro[cite: 23]. [cite_start]Cada estado (`ComAgua`, `SemAgua`, `ComAr`) é encapsulado em sua própria classe, eliminando a necessidade de condicionais complexas e aderindo ao Princípio Aberto/Fechado[cite: 23, 35].
* [cite_start]**Padrão de Projeto Observer (Observador):** Usado para desacoplar o modelo (`Hidrometro`) da visão (`Display`)[cite: 38]. [cite_start]O `Hidrometro` (o *Subject*) notifica o `Display` (o *Observer*) sobre qualquer mudança em seu estado, que então atualiza a interface gráfica sem que haja um acoplamento direto entre eles[cite: 40, 44, 47].

## 🚀 Como Executar o Projeto

### Pré-requisitos

* JDK 17 ou superior.
* Apache Maven 3.6 ou superior.
* Uma IDE Java, como o IntelliJ IDEA (recomendado).

### Passos para Execução

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    ```
2.  **Abra no IntelliJ IDEA:**
    * Abra o IntelliJ e selecione `File > Open...`.
    * Navegue até a pasta do projeto clonado e a abra.
    * Aguarde o IntelliJ sincronizar e baixar todas as dependências do Maven (pode levar um minuto).

3.  **Execute a Aplicação:**
    * No canto direito da IDE, abra a aba vertical **Maven**.
    * Expanda as seções: `[nome-do-projeto] > Plugins > javafx`.
    * Dê um duplo clique no goal **`javafx:run`**.

    A aplicação JavaFX será iniciada, e o servidor da API começará a rodar na porta `7070`.

## 📡 Endpoints da API

A API REST fornece acesso em tempo real aos dados do simulador.

| Endpoint    | Método HTTP | Descrição                                    | Exemplo de Resposta JSON                                          |
|-------------|-------------|------------------------------------------------|-------------------------------------------------------------------|
| `/api/data` | `GET`       | [cite_start]Retorna os dados de medição primários[cite: 65].      | `{"consumoTotalM3":1200.123, "pressaoAtualKpa":345.6, "estado":"EstadoComAgua"}` |
| `/api/status` | `GET`       | [cite_start]Retorna o estado operacional atual[cite: 65].     | `{"consumoTotalM3":1200.123, "pressaoAtualKpa":345.6, "estado":"EstadoComAgua"}` |

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---
Feito com ❤️ por [Seu Nome Aqui].
