package com.udarnyrezhim.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.udarnyrezhim.presentation.focus.BlockedOverlayActivity

class FocusAccessibilityService : AccessibilityService() {

    companion object {
        var isBlockingEnabled = false
        var allowedPackageName: String? = null
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!isBlockingEnabled) return
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return
        val allowed = allowedPackageName

        if (allowed != null && packageName != allowed && !isSystemApp(packageName)) {
            val intent = Intent(this, BlockedOverlayActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }

    override fun onInterrupt() {}

    private fun isSystemApp(packageName: String): Boolean {
        return packageName.startsWith("com.android") ||
                packageName == "com.udarnyrezhim"
    }
}
