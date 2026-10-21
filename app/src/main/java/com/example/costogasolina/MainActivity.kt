package com.example.costogasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CostoGasolinaApp()
            }
        }
    }
}

@Composable
fun CostoGasolinaApp() {


    var precioLitro by remember { mutableStateOf("") }
    var litros by remember { mutableStateOf("") }
    var propina by remember { mutableStateOf("") }
    var agregarPropina by remember { mutableStateOf(false) }


    val precio = precioLitro.toDoubleOrNull() ?: 0.0
    val cantidadLitros = litros.toDoubleOrNull() ?: 0.0
    val cantidadPropina = propina.toDoubleOrNull() ?: 0.0


    val montoGasolina = precio * cantidadLitros

    val montoTotal = if (agregarPropina) {
        montoGasolina + cantidadPropina
    } else {
        montoGasolina
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .padding(horizontal = 16.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "Calcular Monto de Gasolina",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))


            OutlinedTextField(
                value = precioLitro,
                onValueChange = { precioLitro = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Precio por litro de gasolina")
                },
                leadingIcon = {
                    Text(
                        text = "$",
                        fontSize = 22.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = litros,
                onValueChange = { litros = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Litros")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = propina,
                onValueChange = { propina = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Propina")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                enabled = agregarPropina,
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "¿Deseas agregar la propina?",
                    fontSize = 15.sp,
                    color = Color.Black
                )

                Switch(
                    checked = agregarPropina,
                    onCheckedChange = {
                        agregarPropina = it
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = String.format(
                    Locale.US,
                    "Monto Total: $%.2f",
                    montoTotal
                ),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}