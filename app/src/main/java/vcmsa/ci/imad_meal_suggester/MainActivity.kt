package vcmsa.ci.imad_meal_suggester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MealSuggesterScreen()
            }

        }
    }
}

@Composable
fun MealSuggesterScreen() {
    var timeInput by remember { mutableStateOf("") }
    var mealSuggestion by remember { mutableStateOf("Enter a time of day to get a suggestion.") }

    val suggestions = mapOf(
        "morning" to "Breakfast: Pancakes with Honey",
        "mid-morning" to "Snack: Greek Yogurt with Berries",
        "afternoon" to "Lunch: Grilled Chicken Wrap",
        "mid-afternoon" to "Snack: Mixed Nuts",
        "dinner" to "Main Course: Steak with Mashed Potatoes",
        "after dinner" to "Dessert: Chocolate Mousse"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Meal Suggestion App", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = timeInput,
            onValueChange = { timeInput = it },
            label = { Text("Enter time of day (e.g., Morning)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val formattedInput = timeInput.trim().lowercase(Locale.ROOT)
            mealSuggestion = when {
                formattedInput.isEmpty() -> "Please enter a valid time of day."
                suggestions.containsKey(formattedInput) -> suggestions[formattedInput]!!
                else -> "Invalid input. Try Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner, or After Dinner."
            }
        }) {
            Text("Get Meal Suggestion")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(mealSuggestion, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            timeInput = ""
            mealSuggestion = "Enter a time of day to get a suggestion."
        }) {
            Text("Reset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealSuggesterPreview() {
    MaterialTheme {
        MealSuggesterScreen()
    }
}
