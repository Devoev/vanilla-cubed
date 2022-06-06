package net.devoev.vanilla_cubed.item.tool

import net.minecraft.item.*


open class ModSwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings)
    : SwordItem(material, attackDamage, attackSpeed, settings) {

    constructor(data: ToolData) : this(
        data.material, data.swordData.attackDamage, data.swordData.attackSpeed, data.settings
    )
}

open class ModShovelItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings)
    : ShovelItem(material, attackDamage, attackSpeed, settings) {

    constructor(data: ToolData) : this(
        data.material, data.shovelData.attackDamage, data.shovelData.attackSpeed, data.settings
    )
}

open class ModPickaxeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings)
    : PickaxeItem(material, attackDamage, attackSpeed, settings) {

    constructor(data: ToolData) : this(
        data.material, data.pickaxeData.attackDamage, data.pickaxeData.attackSpeed, data.settings
    )
}

open class ModAxeItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings)
    : AxeItem(material, attackDamage, attackSpeed, settings) {

    constructor(data: ToolData) : this(
        data.material, data.axeData.attackDamage, data.axeData.attackSpeed, data.settings
    )
}

open class ModHoeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings)
    : HoeItem(material, attackDamage, attackSpeed, settings) {

    constructor(data: ToolData) : this(
        data.material, data.hoeData.attackDamage, data.hoeData.attackSpeed, data.settings
    )
}