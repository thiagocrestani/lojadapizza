function remover(link){
    // só permitirá o envio se o usuário responder OK
    var resposta = window.confirm("Deseja excluir?");
    if(resposta)
        window.location.href = link;
      
  }
  
  function confirmar(){
    // só permitirá o envio se o usuário responder OK
    var resposta = window.confirm("Deseja realizar a operação?");
    if(!resposta)
        window.location.href = "#";
      
  }
