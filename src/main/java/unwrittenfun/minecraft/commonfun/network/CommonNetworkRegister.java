package unwrittenfun.minecraft.commonfun.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import unwrittenfun.minecraft.commonfun.network.handlers.HandlerTileInteger;
import unwrittenfun.minecraft.commonfun.network.handlers.HandlerTileRequest;
import unwrittenfun.minecraft.commonfun.network.handlers.HandlerTileStack;
import unwrittenfun.minecraft.commonfun.network.messages.MessageTileInteger;
import unwrittenfun.minecraft.commonfun.network.messages.MessageTileRequest;
import unwrittenfun.minecraft.commonfun.network.messages.MessageTileStack;

public class CommonNetworkRegister {
  public SimpleNetworkWrapper wrapper;
  public String modid;
  protected int descriminator = 0;

  public CommonNetworkRegister(String modid) {
    this.modid = modid;
    this.wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);

    registerMessageCommon(HandlerTileInteger.class, MessageTileInteger.class);
    registerMessageCommon(HandlerTileStack.class, MessageTileStack.class);
    registerMessageServer(HandlerTileRequest.class, MessageTileRequest.class);


//    wrapper.registerMessage(HandlerTileInteger.class, MessageTileInteger.class, 0, Side.CLIENT);
//    wrapper.registerMessage(HandlerTileRequest.class, MessageTileRequest.class, 1, Side.CLIENT);
//
//    wrapper.registerMessage(HandlerTileInteger.class, MessageTileInteger.class, 100, Side.SERVER);
//    wrapper.registerMessage(HandlerTileRequest.class, MessageTileRequest.class, 101, Side.SERVER);
  }

  protected <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> messageType, Side side) {
    wrapper.registerMessage(messageHandler, messageType, descriminator, side);
    descriminator++;
  }

  protected <REQ extends IMessage, REPLY extends IMessage> void registerMessageClient(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> messageType) {
    registerMessage(messageHandler, messageType, Side.CLIENT);
  }

  protected <REQ extends IMessage, REPLY extends IMessage> void registerMessageServer(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> messageType) {
    registerMessage(messageHandler, messageType, Side.SERVER);
  }

  protected <REQ extends IMessage, REPLY extends IMessage> void registerMessageCommon(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> messageType) {
    registerMessageClient(messageHandler, messageType);
    registerMessageServer(messageHandler, messageType);
  }
}
