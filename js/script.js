function salvarPessoa() {
    var xmlhttp = new XMLHttpRequest();

    var form = document.getElementById('form-pessoa');
    var formData = new FormData(form);
    var url = "nome=" + formData.get("nome") + 
    "&email=" + formData.get("email") + 
    "&cpf=" + formData.get("cpf") + 
    "&rg=" + formData.get("rg") + 
    "&cnh=" + formData.get("cnh") + 
    "&senha=" + formData.get("senha") + 
    "&cep=" + formData.get("cep") +
    "&rua=" + formData.get("rua") + 
    "&numero=" + formData.get("numero") + 
    "&bairro=" + formData.get("bairro") + 
    "&cidade=" + formData.get("cidade") + 
    "&estado=" + formData.get("estado") + 
    "&telefone=" + formData.get("telefone") + 
    "&celular=" + formData.get("celular");


    if (xmlhttp) {
        xmlhttp.open('get', "http://localhost:880/pessoa?" + url, true);
        xmlhttp.send();
    }
}

function salvarLogin() {
    var xmlhttp = new XMLHttpRequest();

    var form = document.getElementById('form-login');
    var formData = new FormData(form);
    var url = "email=" + formData.get("email") + 
    "&senha=" + formData.get("senha");


    if (xmlhttp) {
        xmlhttp.open('get', "http://localhost:880/login?" + url, true);
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