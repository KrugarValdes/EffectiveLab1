package com.effectivelab1.heroapp.presentation.screens.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel
import com.effectivelab1.heroapp.util.Constants

@Composable
fun ReloadButton(
    viewModel: CharacterViewModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Box(
        modifier =
            modifier
                .size(Constants.reloadButtonSize)
                .background(Color.Gray, shape = CircleShape)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.reload_toast_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                        },
                        onTap = {
                            viewModel.clearDatabaseAndReload()
                        },
                    )
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = stringResource(R.string.reload_button_description),
            modifier = Modifier.size(Constants.reloadIconSize),
            tint = Color.White,
        )
    }
}
