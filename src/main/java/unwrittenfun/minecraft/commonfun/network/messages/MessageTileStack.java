package unwrittenfun.minecraft.commonfun.network.messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MessageTileStack extends MessageTileCoords {
  public ItemStack stack;

  public static MessageTileStack messageFrom(World worldObj, int xCoord, int yCoord, int zCoord, int id, ItemStack stack) {
    MessageTileStack message = new MessageTileStack();
    message.worldId = worldObj.provider.dimensionId;
    message.x = xCoord;
    message.y = yCoord;
    message.z = zCoord;
    message.id = (byte) id;
    message.stack = stack;
    return message;
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    boolean hasStack = buf.readBoolean();

    if (hasStack) {
      stack = ByteBufUtils.readItemStack(buf);
    }
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    if (stack == null) {
      buf.writeBoolean(false);
    } else {
      buf.writeBoolean(true);
      ByteBufUtils.writeItemStack(buf, stack);
    }
  }
}
