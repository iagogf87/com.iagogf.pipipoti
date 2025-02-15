package com.iagogf.pipipoti.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iagogf.pipipoti.R
import com.iagogf.pipipoti.data.entity.EventoEntity
import com.iagogf.pipipoti.ui.navigation.PipiPotiScreen
import com.iagogf.pipipoti.ui.viewmodels.EventoViewModel

@Composable
fun ResumenScreen(
    navController: NavController,
    viewModel: EventoViewModel = hiltViewModel()
) {
    val eventos by viewModel.eventos.collectAsState() // Obtiene la lista de eventos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars) // Evita que quede debajo de la TopAppBar
    ) {
        Text(
            text = "Historial de Eventos",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de eventos con peso para empujar el botón hacia abajo
        Column(
            modifier = Modifier.weight(1f) //Esto hace que la lista empuje el botón al fondo
        ) {
            if (eventos.isEmpty()) {
                Text(
                    text = "No hay eventos registrados aún.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(eventos) { evento ->
                        EventoItem(evento) // Función para mostrar cada evento
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado extra antes del botón

        //Botón abajo para borrar los eventos sin invadir la BottomBar
        Button(
            onClick = {
                viewModel.eliminarTodosLosEventos()
                navController.navigate(PipiPotiScreen.Resumen.route) // Recarga la pantalla
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp) //Espaciado antes de la BottomBar
        ) {
            Text(text = "Borrar Historial")
        }
    }
}


@Composable
fun EventoItem(evento: EventoEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) //Margen interno dentro del card
        ) {
            Spacer(modifier = Modifier.height(8.dp)) //Espaciado interno superior

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (evento.tipo == "Pis") R.drawable.ic_pis2 else R.drawable.ic_caca2
                    ),
                    contentDescription = "Icono de evento",
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = "Fecha: ${evento.fechaHora}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Tipo: ${evento.tipo}", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Resultado: ${evento.resultado}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (evento.resultado == "Conseguido") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) //Espaciado inferior
        }
    }
}
