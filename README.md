Nesta pasta deixarei a base para voce poder replicar onde quiser, basta ter a base em php previamente e seguir os passos.
inicie a conexao com o banco de dados, inicie a sessao, 

BANNER.PHP
inicialmente voce precisa saber  html, css e js para realizar o carousel.
irei deixar um modelo pronto, para voce transformar o carousel em um modelo que voce consegue exibir imagens da tabela.

Consideraremos a tabela lojas para exibir a imagem que aparecerá do banner, em lojas teremos as colunas id, nome, endereco, cep, n, bairro, complemento, whatsapp, telefone, pedido_minimo, logo_icon, cnpj, email, tempo_preparo, descricao.
Ao carregar a pagina carregamos os dados no array para utilizar quando necessario.

Em outra tabela chamada Banner, teremos as colunas id, usuario, loja_id (com vinculo ao id da tabela lojas), imagem, link (quando clicar na imagem), status (que escolhe se vai exibir nesse carousel ou em outro), data_criacao, data_atualizacao.
Ao carregar a pagina carregamos os dados no array para utilizar quando necessario.

Nosso carousel tem 1 imagem por vez e passa uma por vez, podendo ser clicado no botao para carregar a proxima imagem, a anterior, e deve trocar de imagem a cada 3s automaticamente, e toda vez que recarrega a pagina deve mudar a ordem os banners.
Considere usar esse estilo no topo da folha.


<style>
    .carousels { position: relative; width: 100%; overflow: hidden; } 
    .small-carousel { height: 300px; } 
    .small-carousel-inner { display: flex; transition: transform 0.5s ease; width: 100%; } 
    .small-carousel-item { flex: 0 0 100%; } 
    .total { width: 100%; display: block; object-fit: cover; max-height: 300px;} 
    button.prevs, button.nexts { position: absolute; top: 50%; transform: translateY(-50%); background: rgba(0, 0, 0, 0.5); color: #fff; border: none; padding: 10px; cursor: pointer; border-radius: 50%; z-index: 10;}
    button.prevs { left: 10px;}
    button.nexts { right: 10px;}
</style>

Aqui esta o corpo do carousel de exemplo com php, nele conseguimos definir o shuffle que faz com que mostre aleatoriamente as imagens, temos tambem o link da imagem, que permite clicar no link vinculado a imagem na tabela, abrindo em uma nova pagina (target:_blank)

<div class="carousels" style="margin-bottom:30px; margin-top:30px;">
    <div class="small-carousel-inner">
        <?php if (!empty($banners)): shuffle($banners); foreach ($banners as $banner): ?>
                <div class="small-carousel-item">
                    <a href="<?php echo htmlspecialchars($banner['link']); ?>" target="_blank">
                        <img class="total" src="<?php echo htmlspecialchars($banner['imagem']); ?>" alt="Banner">
                    </a>
                </div>
            <?php endforeach; ?>
        <?php endif; ?>
    </div>
    <button class="prevs" onclick="prevSlide4()">❮</button>
    <button class="nexts" onclick="nextSlide4()">❯</button>
</div>

O JS de nosso carousel, ele permite que passe 1 imagem por vez, e passa uma por uma, e a cada 3s troca de imagem.

<script>
let currentIndex4 = 0;
const slidesToShow4 = 1; // Quantidade de imagens exibidas por vez
const items4 = document.querySelectorAll('.small-carousel-item');
function showSlide4() {
    const totalItems = items4.length;
    const maxIndex = totalItems - slidesToShow4;
    currentIndex4 = Math.max(0, Math.min(currentIndex4, maxIndex)); // Restringe o índice
    const offset = -(currentIndex4 * 100); // Multiplica pelo percentual de deslocamento
    document.querySelector('.small-carousel-inner').style.transform = `translateX(${offset}%)`;
}
function nextSlide4() {
    currentIndex4 += slidesToShow4;
    if (currentIndex4 >= items4.length) {
        currentIndex4 = 0; // Volta ao início
    }
    showSlide4();
}
function prevSlide4() {
    currentIndex4 -= slidesToShow4;
    if (currentIndex4 < 0) {
        currentIndex4 = items4.length - slidesToShow4; // Vai para o final
    }
    showSlide4();
}
setInterval(nextSlide4, 3000);
</script>

