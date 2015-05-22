package unwrittenfun.minecraft.commonfun.network.receivers;

import net.minecraft.item.ItemStack;

public interface ITileStackReceiver {
  void receiveStackMessage(byte id, ItemStack stack);
}
