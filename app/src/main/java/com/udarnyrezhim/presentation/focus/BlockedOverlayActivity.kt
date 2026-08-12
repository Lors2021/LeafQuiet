package com.udarnyrezhim.presentation.focus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.udarnyrezhim.presentation.theme.UdarnyRezhimTheme

class BlockedOverlayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UdarnyRezhimTheme(darkTheme = true) {
                BlockedOverlayScreen(
                    onReturn = { finish() }
                )
            }
        }
    }
}
