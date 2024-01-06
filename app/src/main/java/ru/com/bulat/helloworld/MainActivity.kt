package ru.com.bulat.helloworld

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.parcelize.Parcelize

@Parcelize
data class CounterState(
    val number : Int = 0
) : Parcelable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var counterState by rememberSaveable {
                mutableStateOf(CounterState())
            }
            StatelessCounter(
                counterState.number,
                onIncrement = { incrementedValue ->
                    counterState = counterState.copy(number = incrementedValue)
                }
            )
        }
    }
}

@Composable
private fun StatelessCounter(
    counterValue : Int = 0,
    onIncrement : (incrementedValue : Int) -> Unit
) {
    LogCompositionLifeCycle(name = "StatelessCounter")
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = counterValue.toString(),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                onIncrement (counterValue + 1)
            }
        ) {
            Text(
                text = "Increment",
                fontSize = 18.sp
            )
        }
        Box(modifier = Modifier.height(100.dp)){
            if (counterValue % 2 == 0){
                LogCompositionLifeCycle(name = "IsEvenText")
                Text(
                    text = "Is Even = true",
                    fontSize = 18.sp
                )
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewScreen(){
    StatelessCounter(onIncrement = { }, counterValue = 0)
}