package me.tigermouthbear.nebulous.modifiers.misc

import me.tigermouthbear.nebulous.modifiers.IModifier
import java.util.*

/**
 * @author Tigermouthbear
 */

class MemberShuffler: IModifier {
	override fun modify() {
		classes.stream()
		.filter { cn -> !isDependency(cn.name) }
		.forEach { cn ->
			cn.apply {
				shuffle(fields)
				shuffle(methods)
				shuffle(innerClasses)
				shuffle(interfaces)
				shuffle(attrs)
				shuffle(invisibleAnnotations)
				shuffle(visibleAnnotations)
				shuffle(invisibleTypeAnnotations)
				shuffle(visibleTypeAnnotations)
				fields.forEach { fn ->
					fn.apply {
						shuffle(attrs)
						shuffle(invisibleAnnotations)
						shuffle(visibleAnnotations)
						shuffle(invisibleTypeAnnotations)
						shuffle(visibleTypeAnnotations)
					}
				}
				methods.forEach { mn ->
					mn.apply {
						shuffle(attrs)
						shuffle(invisibleAnnotations)
						shuffle(visibleAnnotations)
						shuffle(invisibleTypeAnnotations)
						shuffle(visibleTypeAnnotations)
						shuffle(exceptions)
						shuffle(invisibleLocalVariableAnnotations)
						shuffle(visibleLocalVariableAnnotations)
						shuffle(localVariables)
						shuffle(parameters)
					}
				}
				innerClasses.clear()
			}
		}
	}

	private fun shuffle(list: List<*>?) {
		if(list != null) Collections.shuffle(list)
	}
}