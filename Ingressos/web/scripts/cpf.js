// Valida o CPF
function validateCpf(e)
{
    var inputCpf = $("input[name=cpf]");

    var cpf = inputCpf.val();

    // Adiciona o que foi digitado
    if (!!e)
        cpf += e.data;

    var match = /(\d{3})\.(\d{3})\.(\d{3})-(\d{2})/.exec(cpf);

    if (!match)
    {
        inputCpf[0].setCustomValidity("Digite um CPF válido");
        return false;
    }

    var string = match[1] + match[2] + match[3];
    var array = string.split("").map(c => parseInt(c));

    var n = 0;
    for (var i = 10; i > 1; i--)
        n += i * array[10 - i];

    if (n * 10 % 11 % 10 !== parseInt(match[4][0]))
    {
        inputCpf[0].setCustomValidity("Digite um CPF válido");
        return false;
    }

    array.push(parseInt(match[4][0]));

    n = 0;
    for (var i = 11; i > 1; i--)
        n += i * array[11 - i];

    if (n * 10 % 11 % 10 !== parseInt(match[4][1]))
    {
        inputCpf[0].setCustomValidity("Digite um CPF válido");
        return false;
    }

    inputCpf[0].setCustomValidity("");
}