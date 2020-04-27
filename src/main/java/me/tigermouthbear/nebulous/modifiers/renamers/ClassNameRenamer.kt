package me.tigermouthbear.nebulous.modifiers.renamers

import me.tigermouthbear.nebulous.modifiers.IModifier
import me.tigermouthbear.nebulous.util.Dictionary
import java.util.*

/**
 * @author Tigermouthbear
 */

class ClassNameRenamer: IModifier {
	override fun modify() {
		val remap: MutableMap<String?, String?> = HashMap()

		classes.stream()
		.filter { cn -> !isDependency(cn.name) }
		.forEach { cn ->
			val name = Dictionary.getNewName()
			remap[cn.name] = name
			if(cn.name.replace("/", ".") == manifest.mainAttributes.getValue("Main-Class"))
				manifest.mainAttributes.putValue("Main-Class", name.replace("/", "."))
		}

		applyRemap(remap)
	}
}