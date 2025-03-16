Carousel de Imagens com 1 Imagem Fixa
Índice
Introdução
Estrutura do Código
HTML
CSS
JavaScript
Classes e Elementos
Funcionamento do Carousel
Execução
Ajustes e Melhorias
Introdução
Este projeto implementa um carousel de imagens que exibe três imagens por vez, com uma imagem fixa ao lado. O usuário pode navegar pelas imagens usando botões de próximo e anterior. Quando o usuário clica em uma imagem do carousel, ela substitui a imagem fixa ao lado e registra essa escolha na sessão do navegador.

Estrutura do Código
HTML
O código HTML contém uma estrutura de container que abrange tanto a imagem fixa quanto o carousel deslizante.

CSS
As imagens e os elementos do carousel são estilizados para garantir uma boa apresentação visual e fluidez na navegação.

JavaScript
O JavaScript é responsável por controlar a movimentação das imagens dentro do carousel, permitindo a transição entre elas. Ele também garante que, ao clicar em uma imagem, a imagem fixa seja substituída e a escolha seja registrada na sessão.

Classes e Elementos
.container: Contém a imagem fixa e o carousel.
.fixed-image: Exibe a imagem fixa ao lado do carousel.
.carousel: Container do carousel.
.carousel-inner: Armazena todas as imagens do carousel.
.grid-item: Cada imagem dentro do carousel.
.prev e .next: Botões de navegação para avançar ou retroceder no carousel.
Funcionamento do Carousel
O carousel exibe três imagens por vez e permite que o usuário avance ou retroceda nas imagens. Ao clicar em uma das imagens do carousel, a imagem fixa ao lado é substituída pela imagem escolhida e a escolha é registrada na sessão do navegador, mantendo a opção do usuário.

Execução
Para rodar o projeto:

Abra o arquivo HTML no navegador.
O JavaScript será carregado automaticamente, permitindo a navegação no carousel.
Quando o usuário clicar em uma imagem do carousel, a imagem fixa será atualizada e registrada na sessão.
Ajustes e Melhorias
Melhorar a responsividade para telas menores.
Adicionar animações mais suaves para as transições entre as imagens.
Implementar um loop infinito no carousel para uma experiência de navegação mais fluida.
Criar uma funcionalidade para limpar a escolha da imagem na sessão, se necessário.

