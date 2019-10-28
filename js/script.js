function salvar() {
    var xmlhttp = new XMLHttpRequest();

    var form = document.getElementById('form-pessoa');
    var formData = new FormData(form);
    var url = "nome=" + formData.get("nome") + 
    ";cpf=" + formData.get("cpf") + 
    ";senha=" + formData.get("senha") + 
    ";email=" + formData.get("email"); 

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            // Javascript function JSON.parse to parse JSON data
            var jsonObj = JSON.parse(xmlhttp.responseText);
            document.getElementById("nome").innerHTML = jsonObj.nome;
            console.log(jsonObj.nome);
            document.getElementById("senha").innerHTML = jsonObj.senha;
            console.log(jsonObj.senha);
            document.getElementById("celular").innerHTML = jsonObj.telefone;
            document.getElementById("celular").innerHTML = jsonObj.celular;
            document.getElementById("bairro").innerHTML = jsonObj.bairro;
            
            document.getElementById("   ").style.visibility = "visible";
        }
    }


    if (xmlhttp) {
        xmlhttp.open('get', "http://localhost:880/pessoa?" + url, true);
        xmlhttp.send();
    }
}

//************************ CONJUNTO DE FUNÇÕES PARA IDENTIFICAR O ENDEREÇO ************************
function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('cep').value = ("");
    document.getElementById('rua').value=("");
    document.getElementById('bairro').value=("");
    document.getElementById('cidade').value=("");
}

function meu_callback(conteudo) {
if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('rua').value=(conteudo.logradouro);
    document.getElementById('bairro').value=(conteudo.bairro);
    document.getElementById('cidade').value=(conteudo.localidade);
    console.log(conteudo.bairro + conteudo.logradouro + conteudo.localidade);
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
console.log(cep);
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

        //Cria um elemento javascript.
        var script = document.createElement('script');
        console.log(script);
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
//************************ AS FUNÇÕES DE IDENTIFICAÇÃO DE ENDEREÇO TERMINAM AQUI ************************

//************************ VALIDAÇÃO DE EMAIL ************************
function validaEmail(envelope) {
    usuario = envelope.value.substring(0, envelope.value.indexOf("@"));
    dominio = envelope.value.substring(envelope.value.indexOf("@")+ 1, envelope.value.length);
     
    if ((usuario.length >=1) &&
        (dominio.length >=3) && 
        (usuario.search("@")==-1) && 
        (dominio.search("@")==-1) &&
        (usuario.search(" ")==-1) && 
        (dominio.search(" ")==-1) &&
        (dominio.search(".")!=-1) &&      
        (dominio.indexOf(".") >=1)&& 
        (dominio.lastIndexOf(".") < dominio.length - 1)) {
    document.getElementById("resposta").innerHTML="E-mail válido";
    alert("E-mail valido");
    }
    else{
    document.getElementById("resposta").innerHTML="<font color='green'>E-mail inválido </font>";
    alert("E-mail invalido");
    }
    }