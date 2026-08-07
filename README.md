# 🎬 MoView

Aplicativo Android para consulta de filmes populares, desenvolvido com Kotlin, XML Layouts, MVVM e integração com a API do TMDB.

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![TMDB](https://img.shields.io/badge/API-TMDB-01B4E4?style=for-the-badge&logo=themoviedatabase&logoColor=white)

## 📱 Sobre o projeto

O **MoView** é um aplicativo Android nativo desenvolvido como projeto de portfólio para demonstrar o consumo de uma API REST, a organização do código com MVVM e a construção de interfaces utilizando XML Layouts.

O aplicativo consome a API do **TMDB — The Movie Database** para apresentar uma lista de filmes populares. Ao selecionar um item, o usuário pode visualizar informações como título, pôster, imagem de destaque, sinopse, avaliação e data de lançamento.

A interface observa os dados disponibilizados pelo ViewModel por meio de LiveData, permitindo representar os estados de carregamento, sucesso e falha da requisição.

## 📸 Screenshots

| Catálogo de filmes | Detalhes do filme |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/a25017e8-d2ca-4db0-ad3f-eac576118538" alt="Tela inicial com catálogo de filmes populares" width="250"> | <img src="https://github.com/user-attachments/assets/51c64aec-31f8-4ad4-b8aa-9e125a2feefe" alt="Tela com os detalhes de um filme" width="250"> |

## ✨ Funcionalidades

- **Catálogo de filmes populares:** consulta e apresentação dos filmes retornados pelo TMDB.
- **Detalhes do filme:** exibição do título, pôster, imagem de destaque, sinopse, avaliação e data de lançamento.
- **Carregamento de imagens:** obtenção assíncrona dos pôsteres e imagens com Glide.
- **Indicador de carregamento:** apresentação de progresso enquanto a requisição está sendo executada.
- **Tratamento de erros:** exibição de mensagem quando ocorre uma falha na API ou na conexão.
- **Tentativa de recuperação:** ação **Tentar Novamente** para repetir a consulta após um erro.
- **Navegação entre telas:** fluxo entre o catálogo e os detalhes com Navigation Component.
- **Passagem segura de argumentos:** envio do objeto selecionado entre os Fragments com Safe Args.
- **Suporte ao botão Voltar:** integração da Toolbar com o fluxo de navegação.
- **Proteção da API Key no repositório:** leitura da chave pelo `local.properties`, sem mantê-la diretamente no código-fonte.

## 🏗️ Arquitetura

O projeto utiliza o padrão de apresentação **MVVM — Model-View-ViewModel**, separando os dados, o estado da interface e os componentes visuais.

### Model

Representa os dados recebidos da API do TMDB.

O modelo `Movie` contém:

- identificador;
- título;
- caminho do pôster;
- caminho da imagem de destaque;
- avaliação;
- data de lançamento;
- sinopse.

O modelo implementa `Parcelable`, permitindo que o filme selecionado seja enviado para a tela de detalhes.

### ViewModel

O `HomeViewModel` é responsável por:

- iniciar a consulta dos filmes populares;
- fornecer a API Key para a requisição;
- controlar o estado de carregamento;
- disponibilizar a lista de filmes;
- informar erros da API ou da conexão.

Os dados são expostos para a interface utilizando `LiveData`.

### View

A camada visual é formada por uma Activity e dois Fragments:

- `MainActivity`: hospeda o fluxo de navegação;
- `HomeFragment`: apresenta o catálogo de filmes;
- `DetailFragment`: apresenta as informações do filme selecionado.

A interface utiliza XML Layouts, ViewBinding, RecyclerView e componentes do Material Design.

### Data

A camada de dados contém:

- `RetrofitClient`: configura e disponibiliza a instância do Retrofit;
- `TmdbApiService`: define o endpoint utilizado para consultar filmes populares.

A criação do cliente de rede é centralizada em um objeto Singleton.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Versão | Aplicação no projeto |
|---|---:|---|
| **Kotlin** | 1.9.22 | Linguagem principal do aplicativo. |
| **Android Gradle Plugin** | 8.2.2 | Configuração e compilação do projeto Android. |
| **XML Layouts** | — | Construção das telas do aplicativo. |
| **ViewBinding** | — | Acesso seguro aos componentes dos layouts. |
| **MVVM** | — | Separação entre interface, estado e acesso aos dados. |
| **ViewModel** | 2.7.0 | Gerenciamento dos dados relacionados à interface. |
| **LiveData** | 2.7.0 | Observação das atualizações de estado. |
| **Retrofit** | 2.9.0 | Comunicação com a API REST do TMDB. |
| **Gson Converter** | 2.9.0 | Conversão das respostas JSON em objetos Kotlin. |
| **Glide** | 4.16.0 | Carregamento assíncrono das imagens. |
| **Navigation Component** | 2.7.7 | Navegação entre os Fragments. |
| **Safe Args** | 2.7.7 | Passagem tipada de dados entre destinos. |
| **Material Components** | 1.13.0 | Componentes e padrões visuais da interface. |
| **RecyclerView** | — | Apresentação da lista de filmes. |

## 🌐 API utilizada

O MoView consome a API do **TMDB — The Movie Database**.

O endpoint utilizado para buscar os filmes populares é:

```text
GET https://api.themoviedb.org/3/movie/popular
```

A requisição envia os seguintes parâmetros:

```text
api_key
language=pt-BR
```

Para executar o aplicativo, é necessário possuir uma chave válida da API do TMDB.

## 🔐 Proteção da API Key

A API Key não está escrita diretamente nos arquivos Kotlin do projeto.

O fluxo de configuração funciona da seguinte maneira:

1. A chave é adicionada ao arquivo `local.properties`.
2. O Gradle lê o valor da propriedade `TMDB_API_KEY`.
3. O valor é inserido no `BuildConfig` durante a compilação.
4. O `HomeViewModel` acessa a chave por meio de `BuildConfig.TMDB_API_KEY`.
5. O arquivo `local.properties` permanece ignorado pelo Git.

Configuração no `local.properties`:

```properties
TMDB_API_KEY=sua_chave_da_api_aqui
```

Leitura realizada pelo Gradle:

```kotlin
val apiKey = localProperties.getProperty("TMDB_API_KEY") ?: ""
buildConfigField("String", "TMDB_API_KEY", "\"$apiKey\"")
```

Utilização no aplicativo:

```kotlin
RetrofitClient.instance.getPopularMovies(BuildConfig.TMDB_API_KEY)
```

Essa configuração evita que a chave seja publicada diretamente no repositório. Entretanto, valores incluídos no `BuildConfig` ainda podem ser extraídos de um APK compilado. Em uma aplicação de produção, operações realmente sensíveis devem ser intermediadas por um backend próprio, sem disponibilizar credenciais privilegiadas dentro do aplicativo.

## 📂 Estrutura do projeto

```text
com.example.moview
├── data
│   ├── RetrofitClient.kt
│   └── TmdbApiService.kt
├── model
│   ├── Movie.kt
│   └── MovieResponse.kt
└── ui
    ├── adapter
    │   └── MovieAdapter.kt
    ├── view
    │   ├── MainActivity.kt
    │   ├── HomeFragment.kt
    │   └── DetailFragment.kt
    └── viewmodel
        └── HomeViewModel.kt
```

## 🚧 Desafios técnicos e aprendizados

### 1. Proteção da API Key no repositório

**Desafio:** utilizar a chave do TMDB sem adicioná-la diretamente ao código-fonte ou ao histórico do Git.

**Solução:** leitura da propriedade pelo `local.properties` e disponibilização do valor por meio do `BuildConfig`.

**Aprendizado:** arquivos locais e credenciais não devem ser versionados. Também é importante compreender que ocultar uma chave do repositório não significa torná-la inacessível dentro de um APK compilado.

### 2. Conversão da resposta da API

**Desafio:** mapear corretamente os nomes retornados pelo JSON, como `poster_path`, `backdrop_path`, `vote_average` e `release_date`.

**Solução:** utilização das anotações `@SerializedName` nos modelos Kotlin.

**Aprendizado:** os modelos do aplicativo precisam representar corretamente o contrato fornecido pela API.

### 3. Gerenciamento dos estados da requisição

**Desafio:** informar à tela quando a consulta está em andamento, quando os dados foram carregados e quando ocorreu uma falha.

**Solução:** criação de LiveData específicos para a lista de filmes, o carregamento e as mensagens de erro.

**Aprendizado:** separar esses estados deixa a interface mais previsível e facilita a atualização dos componentes visuais.

### 4. Recuperação após falhas

**Desafio:** permitir uma nova tentativa sem obrigar o usuário a fechar e abrir o aplicativo.

**Solução:** utilização de uma Snackbar com a ação **Tentar Novamente**, que chama novamente `fetchPopularMovies()`.

**Aprendizado:** falhas de conexão precisam oferecer feedback claro e uma ação de recuperação.

### 5. Navegação com objetos complexos

**Desafio:** enviar os dados completos de um filme para a tela de detalhes.

**Solução:** implementação de `Parcelable` no modelo e passagem do objeto utilizando Navigation Component e Safe Args.

**Aprendizado:** a navegação tipada reduz erros relacionados a nomes de argumentos e conversões manuais.

### 6. Ciclo de vida do ViewBinding

**Desafio:** evitar que a referência ao layout do Fragment permaneça ativa depois que sua View é destruída.

**Solução:** manter uma propriedade anulável e defini-la como `null` em `onDestroyView()`.

**Aprendizado:** o ciclo de vida da View de um Fragment é diferente do ciclo de vida do próprio Fragment e deve ser respeitado para evitar vazamentos de memória.

## 🚀 Como executar

### Pré-requisitos

- Android Studio;
- JDK compatível com o projeto;
- Android SDK instalado;
- emulador ou dispositivo Android;
- conta no TMDB;
- chave de API do TMDB;
- conexão com a internet.

O aplicativo possui suporte mínimo ao **Android 7.0 — API 24**.

### 1. Clonar o repositório

```bash
git clone https://github.com/dierlisson/MoView.git
cd MoView
```

### 2. Abrir o projeto

Abra a pasta clonada no Android Studio e aguarde a sincronização do Gradle.

### 3. Configurar a API Key

Abra o arquivo `local.properties`, localizado na raiz do projeto, e adicione:

```properties
TMDB_API_KEY=sua_chave_da_api_aqui
```

Não utilize aspas no valor.

Caso o arquivo ainda não exista, abra o projeto no Android Studio para que ele seja criado ou crie o arquivo manualmente na raiz.

### 4. Executar o aplicativo

1. Inicie um emulador ou conecte um dispositivo Android.
2. Verifique se o dispositivo possui acesso à internet.
3. Selecione o módulo `app`.
4. Clique em **Run**.

## 👤 Autor e contato profissional

Desenvolvido por **Dierlisson Santos Justiniano** como projeto de portfólio em desenvolvimento Android.

- **LinkedIn:** [linkedin.com/in/dierlissonjustiniano](https://www.linkedin.com/in/dierlissonjustiniano/)
- **GitHub:** [github.com/dierlisson](https://github.com/dierlisson)
