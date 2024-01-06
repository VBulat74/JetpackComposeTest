package ru.com.bulat.helloworld

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.remember

private const val TAG = "CompositionLifeCycle"

@Composable
fun LogCompositionLifeCycle(name:String) : Any = remember{
    LifeCycleRememberObserver(name)
}

private class LifeCycleRememberObserver(
    private val name: String
) : RememberObserver {
    override fun onAbandoned() {
        Log.d(TAG,"$name.onAbandoned")
    }

    override fun onForgotten() {
        Log.d(TAG,"$name.onLeave")
    }

    override fun onRemembered() {
        Log.d(TAG,"$name.onEnter")
    }

}