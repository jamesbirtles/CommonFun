package unwrittenfun.minecraft.commonfun;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class CommonFun {
  @SidedProxy(clientSide = "unwrittenfun.minecraft.commonfun.client.ClientProxy", serverSide = "unwrittenfun.minecraft.commonfun.CommonProxy")
  public static CommonProxy proxy;
}
