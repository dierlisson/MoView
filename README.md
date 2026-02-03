# 🎬 MoView - Catálogo de Filmes Android


> *Desafio de desenvolvimento Android Nativo: Consumo de API RESTful com arquitetura moderna.*

## 📱 Sobre o Projeto

O **MoView** é um aplicativo Android nativo desenvolvido como parte de um desafio técnico e portfólio pessoal. O objetivo é demonstrar a implementação de um cliente RESTful robusto, utilizando as melhores práticas de desenvolvimento Android moderno.

O app consome a **API do TMDB (The Movie Database)** para listar filmes populares, exibir detalhes técnicos, sinopse e avaliações, tudo isso gerenciado por uma arquitetura **MVVM** reativa.

---

## 📸 Screenshots

| Tela Inicial (Home) | Detalhes do Filme |
|:---:|:---:|
| <img src="docs/home_screenshot.png" width="200" /> | <img src="docs/detail_screenshot.png" width="200" /> |


---

## 🛠 Tecnologias e Bibliotecas

O projeto foi construído utilizando as ferramentas mais atuais do ecossistema Android:

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Arquitetura:** MVVM (Model-View-ViewModel)
* **Injeção de Dependência:** Manual (Singleton Pattern para Retrofit)
* **Consumo de API:** [Retrofit 2](https://square.github.io/retrofit/) + GSON
* **Carregamento de Imagens:** [Glide](https://github.com/bumptech/glide)
* **Navegação:** [Jetpack Navigation Component](https://developer.android.com/guide/navigation) (Single Activity Architecture)
* **UI Toolkit:** XML Layouts + ViewBinding
* **Gerenciamento de Estado:** LiveData & ViewModel
* **Design:** Material Design 3 Components

---

## ✨ Funcionalidades

* ✅ **Listagem de Filmes:** Exibe os filmes mais populares do momento em uma `RecyclerView` otimizada.
* ✅ **Detalhes Imersivos:** Ao clicar em um filme, o usuário é navegado para uma tela de detalhes com pôster, *backdrop*, sinopse e data de lançamento.
* ✅ **Tratamento de Erros:** Feedback visual (Snackbar) caso haja falha na conexão ou erro na API, com opção de "Tentar Novamente".
* ✅ **Segurança de Dados:** A API Key não é exposta no código-fonte, sendo injetada via `local.properties` e `BuildConfig`.
* ✅ **Navegação Fluida:** Transições de tela gerenciadas pelo Navigation Graph com suporte nativo ao botão "Voltar" na Toolbar.

---

## 🚀 Como Executar o Projeto

Para rodar este projeto localmente, você precisará do Android Studio e de uma chave de API do TMDB.

### Pré-requisitos
1.  Android Studio (Versão Iguana ou superior recomendada).
2.  Conta no [TMDB](https://www.themoviedb.org/) para gerar sua API Key (gratuita).

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/dierlisson/MoView.git](https://github.com/dierlisson/MoView.git)
    ```
2.  **Abra o projeto** no Android Studio.
3.  **Configure a API Key:**
    * Crie um arquivo chamado `local.properties` na raiz do projeto (se não existir).
    * Adicione a seguinte linha, substituindo pela sua chave real:
    ```properties
    sdk.dir=/Caminho/Para/Seu/Android/Sdk
    TMDB_API_KEY=sua_chave_da_api_aqui_sem_aspas
    ```
4.  **Sincronize o projeto** (Sync Project with Gradle Files).
5.  **Execute** em um emulador ou dispositivo físico.

---

## 📂 Estrutura de Pastas

A organização do código segue o padrão de separação de responsabilidades (SoC), facilitando a manutenção e testes:

```text
com.example.moview
├── data          # Camada de Dados (Rede)
│   ├── RetrofitClient.kt  # Configuração do cliente HTTP (Singleton)
│   └── TmdbApiService.kt  # Interface com os endpoints da API
├── model         # Modelos de Dados (Domain)
│   ├── Movie.kt           # Objeto Filme (Parcelable)
│   └── MovieResponse.kt   # Wrapper para a resposta da lista
└── ui            # Camada de Interface (View)
    ├── adapter
    │   └── MovieAdapter.kt      # Adapter da RecyclerView + Glide
    ├── view
    │   ├── MainActivity.kt      # Activity Host do Navigation
    │   ├── HomeFragment.kt      # Tela de Listagem
    │   └── DetailFragment.kt    # Tela de Detalhes
    └── viewmodel
        └── HomeViewModel.kt     # Gerenciamento de Estado (LiveData)
```

---

## 👤 Autor

Desenvolvido por **Dierlisson Justiniano** como parte de um desafio prático de desenvolvimento Android.