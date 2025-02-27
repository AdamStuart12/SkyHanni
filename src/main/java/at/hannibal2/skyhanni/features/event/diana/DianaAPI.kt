package at.hannibal2.skyhanni.features.event.diana

import at.hannibal2.skyhanni.SkyHanniMod
import at.hannibal2.skyhanni.data.IslandType
import at.hannibal2.skyhanni.data.MayorElection
import at.hannibal2.skyhanni.data.PetAPI
import at.hannibal2.skyhanni.utils.InventoryUtils
import at.hannibal2.skyhanni.utils.ItemUtils.getInternalName
import at.hannibal2.skyhanni.utils.LorenzUtils.isInIsland
import at.hannibal2.skyhanni.utils.NEUInternalName.Companion.asInternalName
import net.minecraft.item.ItemStack

object DianaAPI {

    private val spade by lazy { "ANCESTRAL_SPADE".asInternalName() }

    fun hasSpadeInHand() = InventoryUtils.itemInHandId == spade

    private fun isRitualActive() = MayorElection.isPerkActive("Diana", "Mythological Ritual") ||
        MayorElection.isPerkActive("Jerry", "Perkpocalypse") || SkyHanniMod.feature.event.diana.alwaysDiana

    fun hasGriffinPet() = PetAPI.isCurrentPet("Griffin")

    fun isDoingDiana() = IslandType.HUB.isInIsland() && isRitualActive() && hasSpadeInInventory()

    val ItemStack.isDianaSpade get() = getInternalName() == spade

    private fun hasSpadeInInventory() = InventoryUtils.getItemsInOwnInventory().any { it.isDianaSpade }
}
