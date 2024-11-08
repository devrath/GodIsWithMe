package com.istudio.godiswithme.architecture.features.home.settings

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun Settings() {
   var expanded by remember { mutableStateOf(false) }
   var selectedLanguage by remember { mutableStateOf("English") }

   // Box to center the content
   Box(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),  // Padding for outer container
      contentAlignment = Alignment.Center
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()  // Ensures content takes the height it needs
      ) {
         // First Option - Select Language
         Text(
            text = "Select Language: $selectedLanguage",
            modifier = Modifier
               .fillMaxWidth()
               .clickable { expanded = !expanded }
               .padding(8.dp), // Padding for the clickable text
            style = MaterialTheme.typography.bodyLarge
         )

         // Dropdown menu for language selection
         DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.align(Alignment.CenterHorizontally)  // Center the dropdown
         ) {

            val cxt = LocalContext.current
            listOf("English", "Kannada", "Hindi").forEach { language ->
               DropdownMenuItem(
                  onClick = {
                     selectedLanguage = language
                     expanded = false

                     val locale = when (language) {
                        "Kannada" -> Locale("kn")  // Kannada Locale
                        "Hindi" -> Locale("hi")  // Hindi Locale
                        else -> Locale("en")  // Default to English
                     }

                     val config = Configuration(cxt.resources.configuration)
                     config.setLocale(Locale(locale.toString()))  // Set the new locale
                     cxt.createConfigurationContext(config)  // Apply the configuration
                     // You may need to update the context's resources
                     cxt.resources.updateConfiguration(config, cxt.resources.displayMetrics)
                     /*val intent = Intent(cxt, cxt::class.java)
                     cxt.finish()
                     cxt.startActivity(intent)*/
                  },
                  text = { Text(text = language) },  // Providing text here
                  modifier = Modifier.fillMaxWidth(),  // Optional: fill max width for better layout
                  leadingIcon = {
                     Icon(
                        imageVector = Icons.Default.Language, // Optional: Add an icon
                        contentDescription = null
                     )
                  },
                  trailingIcon = {
                     if (selectedLanguage == language) {
                        Icon(
                           imageVector = Icons.Default.Check, // Optional: Add checkmark when selected
                           contentDescription = null
                        )
                     }
                  },
                  enabled = true,  // Enable the item
                  colors = MenuDefaults.itemColors(
                     disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                     textColor = MaterialTheme.colorScheme.onSurface
                  ),
                  contentPadding = PaddingValues(start = 16.dp, end = 16.dp)  // Adjust padding
               )
            }
         }
      }
   }
}