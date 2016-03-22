$(document).ready(function() {
        $('#ContatosTable').jtable({
                title : 'Students List',
               
                actions : {
                        listAction : '/contatos/listarJson',
                        createAction : '/contatos/adicionarJson',
                        updateAction : '/contatos/editarJson',
                        deleteAction : '/contatos/deletarJson'
                },
                fields : {
                        id : {
                                title : 'Contato Id',
                                width : '30%',
                                key : true,
                                list : true,
                                edit : false,
                                create : false	
                        },
                        nome : {
                                title : 'Nome',
                                width : '30%',
                                edit : true
                        },
                        telefone : {
                                title : 'Telefone',
                                width : '20%',
                                edit : true
                        },
                        email : {
                                title : 'Email',
                                width : '20%',
                                edit : true
                        }
                }, 
                
                formCreated: function(event, data){
                	data.form.find('input[name="nome"]').addClass('validate[required]');
                	data.form.find('input[name="telefone"]').addClass('validate[required]');
                	data.form.find('input[name="email"]').addClass('validate[required, custom[email]]');
                }, 
                formSubmitting: function (event, data) {
                    return data.form.validationEngine('validate');
                },
                
                formClosed: function (event, data) {
                    data.form.validationEngine('hide');
                    data.form.validationEngine('detach');
                }
        });
        $('#ContatosTable').jtable('load');
});