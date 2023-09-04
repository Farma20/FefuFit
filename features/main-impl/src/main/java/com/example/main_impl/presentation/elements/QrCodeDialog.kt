package com.example.main_impl.presentation.elements

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun QrCodeDialog(openDialog: MutableState<Boolean>, qrCode: Bitmap) {
    Dialog(
        onDismissRequest = { openDialog.value = false }
    ) {
        Surface(
            modifier = Modifier
                .size(250.dp)
                .shadow(elevation = 4.dp)
                .clip(
                    RoundedCornerShape(12.dp)
                )
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Image(
                        bitmap = qrCode.asImageBitmap(),
                        contentDescription = "qrCode",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }
    }
}