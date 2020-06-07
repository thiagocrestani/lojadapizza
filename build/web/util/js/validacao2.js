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
                         numeroend: {
                            required: true,
                            number: true,
                            
                        }

                    },
                    messages: {
                        nome: {
                            required: "Campo obrigat&oacuterio"
                        },
                        numeroend: {
                            required: "Campo obrigat&oacuterio",
                            number: "Apenas n&uacutemeros" ,               
                        }           
                    }
                               

                });
                onfocusout: true
            });