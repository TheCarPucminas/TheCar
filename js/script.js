function salvar() {
    var xmlhttp = new XMLHttpRequest();

    var form = document.getElementById('form-pessoa');
    var formData = new FormData(form);

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            // Javascript function JSON.parse to parse JSON data
            var jsonObj = JSON.parse(xmlhttp.responseText);

            document.getElementById("id").innerHTML = jsonObj.id;
            document.getElementById("descricao").innerHTML = jsonObj.descricao;
            document.getElementById("preco").innerHTML = jsonObj.preco;
            document.getElementById("quant").innerHTML = jsonObj.quant;
            document.getElementById("dataFabricacao").innerHTML = jsonObj.dataFabricacao;
            
            document.getElementById("product-result").style.visibility = "visible";
        }
    }

    if (xmlhttp) {
        xmlhttp.open('post', "http://localhost:880/pessoa", true);
        xmlhttp.send(formData);
    }
}


function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value=("");
    document.getElementById('bairro').value=("");
    document.getElementById('cidade').value=("");
    document.getElementById('uf').value=("");
}

function meu_callback(conteudo) {
if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('rua').value=(conteudo.logradouro);
    document.getElementById('bairro').value=(conteudo.bairro);
    document.getElementById('cidade').value=(conteudo.localidade);
    document.getElementById('uf').value=(conteudo.uf);
} //end if.
else {
    //CEP não Encontrado.
    limpa_formulário_cep();
    alert("CEP não encontrado.");
}
}

function pesquisacep(valor) {

//Nova variável "cep" somente com dígitos.
var cep = valor.replace(/\D/g, '');

//Verifica se campo cep possui valor informado.
if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if(validacep.test(cep)) {

        //Preenche os campos com "..." enquanto consulta webservice.
        document.getElementById('rua').value="...";
        document.getElementById('bairro').value="...";
        document.getElementById('cidade').value="...";
        document.getElementById('uf').value="...";

        //Cria um elemento javascript.
        var script = document.createElement('script');

        //Sincroniza com o callback.
        script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

        //Insere script no documento e carrega o conteúdo.
        document.body.appendChild(script);

    } //end if.
    else {
        //cep é inválido.
        limpa_formulário_cep();
        alert("Formato de CEP inválido.");
    }
} //end if.
else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep();
}
};

