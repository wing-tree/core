package com.wing.tree.bruni.core.extension

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.core.view.isVisible
import com.wing.tree.bruni.core.constant.ONE
import com.wing.tree.bruni.core.constant.ZERO

val View.isGone: Boolean get() = visibility == View.GONE
val View.isInvisible: Boolean get() = visibility == View.INVISIBLE
val View.isNotVisible: Boolean get() = isVisible.not()

val View.isNotShown: Boolean get() = isShown.not()

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.collapseVertically(
    duration: Long,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null
) {
    val measuredHeight: Int = this.measuredHeight
    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            layoutParams.height = ZERO

            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            alpha = ZERO.float
            layoutParams.height = ZERO

            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(measuredHeight, ZERO).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
    }.start()
}

fun View.expandVertically(
    duration: Long,
    value: Int,
    onAnimationStart: ((animation: Animator) -> Unit)? = null,
    onAnimationEnd: ((animation: Animator) -> Unit)? = null,
    onAnimationCancel: ((animation: Animator) -> Unit)? = null,
    onAnimationRepeat: ((animation: Animator) -> Unit)? = null
) {
    layoutParams.height = height

    val listener = object : AnimatorListener {
        override fun onAnimationStart(animation: Animator) {
            onAnimationStart?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            layoutParams.height = value

            onAnimationEnd?.invoke(animation)
        }

        override fun onAnimationCancel(animation: Animator) {
            layoutParams.height = value

            onAnimationCancel?.invoke(animation)
        }

        override fun onAnimationRepeat(animation: Animator) {
            onAnimationRepeat?.invoke(animation)
        }
    }

    ValueAnimator.ofInt(height, value).apply {
        addListener(listener)
        addUpdateListener {
            val animatedValue = it.animatedValue

            if (animatedValue is Int) {
                alpha = animatedValue.div(value.float)
                layoutParams.height = animatedValue

                requestLayout()
            }
        }

        this.duration = duration
    }.start()
}

fun View.fadeIn(
    duration: Long,
    interpolator: TimeInterpolator? = context.decelerateQuadInterpolator,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ZERO.float

    return animate()
        .alpha(ONE.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .withLayer()
}

fun View.fadeOut(
    duration: Long,
    interpolator: TimeInterpolator? = context.accelerateQuadInterpolator,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    alpha = ONE.float

    return animate()
        .alpha(ZERO.float)
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .withLayer()
}

fun View.rotate(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotation(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.rotateX(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotationX(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.rotateY(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .rotationY(value)
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
        .withLayer()
}

fun View.translateDown(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationY(value)
        .withLayer()
}

fun View.translateDown(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationY(value)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationX(value.negative)
        .withLayer()
}

fun View.translateLeft(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationX(value.negative)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateRight(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationX(value)
        .withLayer()
}

fun View.translateRight(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationX(value)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}

fun View.translateUp(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    listener: AnimatorListener? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .setListener(listener)
        .translationY(value.negative)
        .withLayer()
}

fun View.translateUp(
    duration: Long,
    value: Float,
    interpolator: TimeInterpolator? = null,
    withStartAction: Runnable? = null,
    withEndAction: Runnable? = null
): ViewPropertyAnimator {
    return animate()
        .setDuration(duration)
        .setInterpolator(interpolator)
        .translationY(value.negative)
        .withLayer()
        .withStartAction(withStartAction)
        .withEndAction(withEndAction)
}
