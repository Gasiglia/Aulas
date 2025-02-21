
    let currentIndex = 0;
    
    let slidesToShow = 2; // Valor inicial para telas grandes

// Função para atualizar a quantidade de slides a serem exibidos
function updateSlidesToShow() {
    if (window.innerWidth >= 1110) {
        slidesToShow = 3; // Exibe 2 slides para telas menores que 768px
    } else {
        slidesToShow = 2; // Exibe 3 slides para telas maiores que 768px
    }
}

    const items = document.querySelectorAll('.grid-item');

    function showSlide(index) {
        const totalItems = items.length;
        currentIndex = Math.max(0, Math.min(index, totalItems - slidesToShow));
        const offset = -(currentIndex * (100 / slidesToShow));
        document.querySelector('.carousel-inner').style.transform = `translateX(${offset}%)`;
    }

    function nextSlide() {
        if (currentIndex + slidesToShow < items.length) {
            showSlide(currentIndex + slidesToShow);
        } else {
            showSlide(items.length - slidesToShow);
        }
    }

    function prevSlide() {
        if (currentIndex - slidesToShow >= 0) {
            showSlide(currentIndex - slidesToShow);
        } else {
            showSlide(0);
        }
    }
// Função para mudar a imagem fixa e registrar na sessão
function changeFixedImage(imageSrc) {
    // Troca a imagem fixa
    document.getElementById('fixedImage').src = imageSrc;

    // Registra a escolha na sessão
    sessionStorage.setItem('personagemEscolhido', imageSrc);
}

// Adiciona o evento de clique para cada imagem do carousel
document.querySelectorAll('.carousel .grid-item img').forEach(image => {
    image.addEventListener('click', function() {
        changeFixedImage(this.src);
    });
});

// Verifica se já existe um personagem escolhido na sessão e atualiza a imagem fixa ao carregar a página
window.onload = function() {
    const personagemEscolhido = sessionStorage.getItem('personagemEscolhido');
    if (personagemEscolhido) {
        document.getElementById('fixedImage').src = personagemEscolhido;
    }
};
