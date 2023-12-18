package cnovaez.dev.moviesbillboard.utils.ext

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import java.util.Timer

/**
 ** Created by Carlos A. Novaez Guerrero on 17/12/2023 23:16
 ** cnovaez.dev@outlook.com
 **/
class TimerSaver : Saver<Timer, Int> {
    override fun SaverScope.save(value: Timer): Int {
        return value.hashCode()
    }

    override fun restore(value: Int): Timer? {
        return Timer()
    }
}