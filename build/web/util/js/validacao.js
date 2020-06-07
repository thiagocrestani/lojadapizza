
$(document).ready(function () {
    
                $("#form").validate({
                   
                    debug: true,
                    submitHandler: function(form){
				form.submit();
		    },
                    rules: {
                        nome: {
                            required: true,
                        },
                        rua: {
                            required: true,
                        },
                        email: {
                            required: true,
                            email: true,
                        },
                        ddd: {
                            required: true,
                            number: true,
                            maxlength: 2,
                            minlength: 2
                        },
                        telefone: {
                            required: true,
                            number: true,
                            maxlength: 9,
                            minlength: 8
                        },
                         numero: {
                            required: true,
                            number: true
                            
                        },
                        
                        formapagamento: {
                            required: true,        
                        }

                    },
                    messages: {
                        nome: {
                            required: "Campo obrigat&oacuterio"
                        },
                        rua: {
                            required: "Campo obrigat&oacuterio"
                        },
                        email: {
                            required: "Campo obrigat&oacuterio",
                            email: "Email incorreto"
                        },
                        ddd: {
                            required: "Campo obrigat&oacuterio",
                            number: "Apenas números",
                            maxlength: "O c&oacutedigo deve ter 2 d&iacutegitos",
                            minlength: "O c&oacutedigo deve ter 2 d&iacutegitos"
                        },
                        telefone: {
                            required: "Campo obrigat&oacuterio",
                            number: "Apenas n&uacutemeros",
                            maxlength: "O telefone deve ter no m&aacuteximo 9 d&iacutegitos",
                            minlength: "O telefone deve ter no m&iacutenimo 8 d&iacutegitos"
                        },
                        numero: {
                            required: "Campo obrigat&oacuterio",
                            number: "Apenas n&uacutemeros"               
                        },
                        formapagamento: {
                            required: "Escolha uma forma de pagamento"
                                       
                        }
                    }
                               

                });
                onfocusout: true
            });