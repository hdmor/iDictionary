package com.i.dictionary.feature.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.i.dictionary.feature.domain.model.Word

@Composable
fun WordItem(word: Word, modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Text(text = word.word, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = word.phonetic, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = word.sourceUrls.toString())
        word.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { Text(text = "Example: $it") }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}