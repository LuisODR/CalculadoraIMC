package upf.edu.calculadoraImc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import upf.edu.calculadoraImc.ui.theme.CalculadoraIMCTheme



//Ter uma classe Pessoa com propriedades para nome, peso (em kg) e altura (em metros).
//
//Ter uma função calcularIMC na classe Pessoa que retorne o IMC. A fórmula do IMC é: peso / (altura * altura).
//
//Ter uma função classificarIMC na classe Pessoa que retorne uma string com a classificação do IMC, seguindo a tabela:
//
//IMC	Classificação
//Abaixo de 18.5
//
//Abaixo do peso
//
//18.5 a 24.9
//
//Peso normal
//
//25.0 a 29.9
//
//Sobrepeso
//
//30.0 a 34.9
//
//Obesidade grau 1
//
//35.0 a 39.9
//
//Obesidade grau 2
//
//40.0 ou mais
//
//Obesidade grau 3
//
//Criar instâncias da classe Pessoa no main e imprimir o nome, IMC e classificação de cada pessoa.

//    CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCScreen()

        }
    }
}

@Composable
fun IMCScreen() {
    var peso by remember{ mutableStateOf(TextFieldValue(""))};
    var altura by remember { mutableStateOf(TextFieldValue(""))}
    var resultado by remember{ mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calculadora de IMC",
            fontSize = 24.sp, // Tamanho da fonte
            modifier = Modifier
                .padding(bottom = 24.dp) // Espaçamento abaixo do texto
        )


        // Campo de entrada para o peso
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada para a altura
        OutlinedTextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))



        // Botão para calcular o IMC
        Button(
            onClick = {
                val valorPeso = peso.text.replace(",", ".").toDoubleOrNull()
                val valorAltura = altura.text.replace(",", ".").toDoubleOrNull()
                if (valorPeso != null && valorAltura != null && valorAltura > 0) {
                    val imc = valorPeso / (valorAltura * valorAltura)
                    val classificacao = classificarIMC(imc)
                    resultado = String.format("Seu IMC é: %.2f (%s)", imc, classificacao)
                } else {
                    resultado = "Por favor, insira valores válidos."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular IMC")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para limpar os campos
        Button(
            onClick = {
                peso = TextFieldValue("")
                altura = TextFieldValue("")
                resultado = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Exibe o resultado usando a função Composable separada
        displayResultado(resultado)

    }
}

// Função Composable para exibir o resultado
@Composable
fun displayResultado(resultado: String) {
    Text(
        text = resultado,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

fun classificarIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        imc < 35.0 -> "Obesidade grau 1"
        imc < 40.0 -> "Obesidade grau 2"
        else -> "Obesidade grau 3"
    }
}





