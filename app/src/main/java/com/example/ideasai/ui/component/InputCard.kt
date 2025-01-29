package com.example.ideasai.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ideasai.R

@Composable
fun InputCard(
    about: String,
    purpose: String,
    topics: String,
    lang: String,
    onAboutChange: (String) -> Unit,
    onPurposeChange: (String) -> Unit,
    onTopicsChange: (String) -> Unit,
    onLangChange: (String) -> Unit,
    onGenerateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FormTextField(
                value = about,
                label = stringResource(R.string.idea_about_label),
                placeholder = stringResource(R.string.idea_about_hint),
                onValueChange = onAboutChange
            )

            FormTextField(
                value = purpose,
                label = stringResource(R.string.idea_purpose_label),
                placeholder = stringResource(R.string.idea_purpose_hint),
                onValueChange = onPurposeChange
            )

            FormTextField(
                value = topics,
                label = stringResource(R.string.idea_topics_label),
                placeholder = stringResource(R.string.idea_topics_hint),
                onValueChange = onTopicsChange
            )

            FormTextField(
                value = lang,
                label = stringResource(R.string.idea_lang_label),
                placeholder = stringResource(R.string.idea_lang_hint),
                onValueChange = onLangChange
            )

            SubmitButton(
                enabled = about.isNotBlank() && purpose.isNotBlank() && topics.isNotBlank(),
                onClick = onGenerateClick
            )
        }
    }
}