package unwrittenfun.minecraft.commonfun.network.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unwrittenfun.minecraft.commonfun.CommonFun;
import unwrittenfun.minecraft.commonfun.network.messages.MessageTileStack;
import unwrittenfun.minecraft.commonfun.network.receivers.ITileStackReceiver;

public class HandlerTileStack implements IMessageHandler<MessageTileStack, IMessage> {
  @Override
  public IMessage onMessage(MessageTileStack message, MessageContext ctx) {
    World world = CommonFun.proxy.getWorldForId(message.worldId, ctx.side);
    if (world != null) {
      TileEntity tileEntity = world.getTileEntity(message.x, message.y, message.z);
      if (tileEntity instanceof ITileStackReceiver) {
        ((ITileStackReceiver) tileEntity).receiveStackMessage(message.id, message.stack);
      }
    }
    return null;
  }
}