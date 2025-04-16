<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Conversor de Moedas</title>
</head>
<body>
  <h1>Conversor de Moedas</h1>
  <div id="resultado"></div>

  <script>
    async function obterTaxas(base) {
      const response = await fetch(`https://open.er-api.com/v6/latest/${base}`);
      const data = await response.json();
      return data.rates;
    }

    async function converterMoeda(base, destino, valor) {
      const taxas = await obterTaxas(base);
      const taxa = taxas[destino];
      const resultadoDiv = document.getElementById("resultado");

      if (!taxa) {
        resultadoDiv.innerHTML = "Moeda de destino inválida.";
        return;
      }

      const resultado = valor * taxa;
      resultadoDiv.innerHTML = `${valor} ${base} = <strong>${resultado.toFixed(2)} ${destino}</strong>`;
    }

    async function exibirMenu() {
      const opcoes = [
        { de: "USD", para: "BRL" },
        { de: "BRL", para: "USD" },
        { de: "EUR", para: "BRL" },
        { de: "BRL", para: "EUR" },
        { de: "USD", para: "EUR" },
        { de: "EUR", para: "USD" }
      ];

      let menu = "Escolha uma conversão:\n";
      opcoes.forEach((op, index) => {
        menu += `${index + 1}. ${op.de} -> ${op.para}\n`;
      });

      const escolha = prompt(menu);
      const index = parseInt(escolha) - 1;

      if (index >= 0 && index < opcoes.length) {
        const valor = parseFloat(prompt(`Digite o valor em ${opcoes[index].de}:`));
        if (!isNaN(valor)) {
          await converterMoeda(opcoes[index].de, opcoes[index].para, valor);
        } else {
          document.getElementById("resultado").innerHTML = "Valor inválido.";
        }
      } else {
        document.getElementById("resultado").innerHTML = "Opção inválida.";
      }
    }

    exibirMenu();
  </script>
</body>
</html>
