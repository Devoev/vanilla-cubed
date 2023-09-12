package net.devoev.vanilla_cubed.item

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import net.devoev.vanilla_cubed.data.client.MOD_TRIM_MATERIALS
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials
import net.minecraft.util.Identifier

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {

    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        with(itemModelGenerator) {
            createArmor(ModItems.armorItems)
        }
    }

    /**
     * Creates models and registers the given [armorItems].
     */
    private fun ItemModelGenerator.createArmor(armorItems: Collection<ArmorItem>)
        = armorItems.forEach { registerArmor(it) } // TODO: Replace with createArmorItem

    /**
     * Registers the given [armor] and makes it compatible with all [MOD_TRIM_MATERIALS].
     *
     * @see ItemModelGenerator.createArmor
     */
    private fun ItemModelGenerator.createModArmor(armor: ArmorItem) {
        val identifier = ModelIds.getItemModelId(armor)
        val identifier2 = TextureMap.getId(armor)
        val identifier3 = TextureMap.getSubId(armor, "_overlay")

        if (armor.material == ArmorMaterials.LEATHER) {
            Models.GENERATED_TWO_LAYERS.upload(identifier, TextureMap.layered(identifier2, identifier3), this.writer) { id, textures ->
                this.createModArmorJson(id, textures, armor.material)
            }
        } else {
            Models.GENERATED.upload(identifier, TextureMap.layer0(identifier2), this.writer) { id, textures ->
                this.createModArmorJson(id, textures, armor.material)
            }
        }

        for (trimMaterial in MOD_TRIM_MATERIALS) {
            val string = trimMaterial.appliedName(armor.material)
            val identifier4: Identifier = this.suffixTrim(identifier, string)
            val string2 = armor.type.getName() + "_trim_" + string
            val identifier5 = Identifier(string2).withPrefixedPath("trims/items/")
            if (armor.material === ArmorMaterials.LEATHER) {
                this.uploadArmor(identifier4, identifier2, identifier3, identifier5)
                continue
            }
            this.uploadArmor(identifier4, identifier2, identifier5)
        }
    }

    /**
     * Creates a `json` file for the given [armorMaterial] and makes it compatible with all [MOD_TRIM_MATERIALS]
     *
     * @see ItemModelGenerator.createArmorJson
     */
    private fun ItemModelGenerator.createModArmorJson(id: Identifier, textures: Map<TextureKey, Identifier>, armorMaterial: ArmorMaterial): JsonObject? {
        val jsonObject = Models.GENERATED_TWO_LAYERS.createJson(id, textures)
        val jsonArray = JsonArray()
        for (trimMaterial in MOD_TRIM_MATERIALS) {
            val jsonObject2 = JsonObject()
            val jsonObject3 = JsonObject()
            jsonObject3.addProperty(ItemModelGenerator.TRIM_TYPE.path, trimMaterial.itemModelIndex)
            jsonObject2.add("predicate", jsonObject3)
            jsonObject2.addProperty("model", suffixTrim(id, trimMaterial.appliedName(armorMaterial)).toString())
            jsonArray.add(jsonObject2)
        }
        jsonObject.add("overrides", jsonArray)
        return jsonObject
    }
}