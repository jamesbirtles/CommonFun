package unwrittenfun.minecraft.commonfun.network.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import unwrittenfun.minecraft.commonfun.CommonFun;
import unwrittenfun.minecraft.commonfun.network.messages.MessageTileRequest;
import unwrittenfun.minecraft.commonfun.network.receivers.ITileRequestReceiver;

public class HandlerTileRequest implements IMessageHandler<MessageTileRequest, IMessage> {
  @Override
  public IMessage onMessage(MessageTileRequest message, MessageContext ctx) {
    World world = CommonFun.proxy.getWorldForId(message.worldId, ctx.side);
    if (world != null) {
      TileEntity tileEntity = world.getTileEntity(message.x, message.y, message.z);
      if (tileEntity instanceof ITileRequestReceiver) {
        ((ITileRequestReceiver) tileEntity).receiveRequestMessage(message.id, ctx.getServerHandler().playerEntity);
      }
    }
    return null;
  }
}
