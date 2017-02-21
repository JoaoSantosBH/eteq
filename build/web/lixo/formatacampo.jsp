<%-- 
    Document   : formatacampo
    Created on : Jun 8, 2014, 11:34:51 AM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>Formatacao de campos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script language="JavaScript" type="text/javascript" >

            //adiciona mascara ao telefone
            function MascaraTelefone(tel) {
                if (mascaraInteiro(tel) == false) {
                    event.returnValue = false;
                }
                return formataCampo(tel, '0000-0000', event);
            }
            //valida telefone
            function ValidaTelefone(tel) {
                exp = /(\d{4})-(\d{4})/g
                if (!exp.test(tel.value))
                    alert('Numero de Telefone Invalido!');
                alert(exp);
            }
            //valida numero inteiro com mascara
            function mascaraInteiro() {
                if (event.keyCode < 48 || event.keyCode > 57) {
                    event.returnValue = false;
                    return false;
                }
                return true;
            }



            //formata de forma generica os campos
            function formataCampo(campo, Mascara, evento) {
                var boleanoMascara;

                var Digitato = evento.keyCode;
                exp = /\-|\.|\/|\(|\)| /g
                campoSoNumeros = campo.value.toString().replace(exp, "");

                var posicaoCampo = 0;
                var NovoValorCampo = "";
                var TamanhoMascara = campoSoNumeros.length;
                ;

                if (Digitato !== 8) { // backspace 
                    for (i = 0; i <= TamanhoMascara; i++) {
                        
                        boleanoMascara = ((Mascara.charAt(i) === "-") )
                        
                        boleanoMascara = boleanoMascara
                        
                        if (boleanoMascara) {
                            NovoValorCampo += Mascara.charAt(i);
                            TamanhoMascara++;
                        } else {
                            NovoValorCampo += campoSoNumeros.charAt(posicaoCampo);
                            posicaoCampo++;
                        }
                    }
                    campo.value = NovoValorCampo;
                    return true;
                } else {
                    return true;
                }
            }
        </script> 

    </head>
    <body>
        <form name="form1">

            <input type="text" name="tel" onKeyPress="MascaraTelefone(form1.tel);"  maxlength="9"  onBlur="ValidaTelefone(form1.tel);">

  <input type="text" name="t" >
        </form>
    </body>
</html>
