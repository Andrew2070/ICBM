package icbm.zhapin.render.tile;

import icbm.zhapin.jiqi.BJiQi.JiQi;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RHJiQi implements ISimpleBlockRenderingHandler
{
	public static final int ID = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == ID)
		{
			GL11.glPushMatrix();

			if (metadata < JiQi.FaSheDi.ordinal() * 3 + 3)
			{
				int tier = metadata;

				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glScalef(0.4f, 0.4f, 0.4f);

				if (tier == 0)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheDi.TEXTURE_FILE_0);
					RFaSheDi.modelBase0.render(0.0625F);
					RFaSheDi.modelRail0.render(0.0625F);
				}
				else if (tier == 1)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheDi.TEXTURE_FILE_1);

					RFaSheDi.modelBase1.render(0.0625F);
					RFaSheDi.modelRail1.render(0.0625F);
					GL11.glRotatef(180F, 0F, 180F, 1.0F);
					RFaSheDi.modelRail1.render(0.0625F);
				}
				else if (tier == 2)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheDi.TEXTURE_FILE_2);
					RFaSheDi.modelBase2.render(0.0625F);
					RFaSheDi.modelRail2.render(0.0625F);
					GL11.glRotatef(180F, 0F, 180F, 1.0F);
					RFaSheDi.modelRail2.render(0.0625F);
				}
			}
			else if (metadata < JiQi.FaSheShiMuo.ordinal() * 3 + 3)
			{
				int tier = metadata - 3;
				GL11.glTranslatef(0f, 0.9f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glRotatef(180f, 0f, 180f, 1f);

				if (tier == 0)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheShiMuo.TEXTURE_FILE_0);
					RFaSheShiMuo.model0.render(0.0625F);
				}
				else if (tier == 1)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheShiMuo.TEXTURE_FILE_1);
					RFaSheShiMuo.model1.render(0.0625F);
				}
				else if (tier == 2)
				{
					FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheShiMuo.TEXTURE_FILE_2);
					RFaSheShiMuo.model2.render(0.0625F);
				}
			}
			else if (metadata < JiQi.FaSheJia.ordinal() * 3 + 3)
			{
				int tier = metadata - 6;
				GL11.glTranslatef(0f, -0.1f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glScalef(0.8f, 0.4f, 0.8f);

				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RFaSheJia.TEXTURE_FILE);

				RFaSheJia.MODEL.render(0.0625F);
			}
			else if (metadata == JiQi.LeiDaTai.ordinal() + 6)
			{
				GL11.glTranslatef(0f, 1f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glRotatef(180f, 0, 1, 0);

				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RLeiDaTai.TEXTURE_FILE);

				RLeiDaTai.MODEL.render(0.0625f, 0, 1.2f);
			}
			else if (metadata == JiQi.DianCiQi.ordinal() + 6)
			{
				GL11.glTranslatef(0f, 0.3f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glScalef(0.6f, 0.6f, 0.6f);

				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RDianCiQi.TEXTURE_FILE);

				RDianCiQi.MODEL.render(0, 0.0625F);
			}
			else if (metadata == JiQi.XiaoFaSheQi.ordinal() + 6)
			{
				GL11.glTranslatef(0f, 0.4f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glScalef(0.55f, 0.5f, 0.55f);

				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RXiaoFaSheQi.TEXTURE_FILE);

				RXiaoFaSheQi.MODEL0.render(0.0625F);
				RXiaoFaSheQi.MODEL1.render(0.0625F);
			}
			else if (metadata == JiQi.YinDaoQi.ordinal() + 6)
			{
				GL11.glTranslatef(0f, 1.1f, 0f);
				GL11.glRotatef(180f, 0f, 0f, 1f);
				GL11.glRotatef(180f, 0f, 1f, 0f);

				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RYinDaoQi.TEXTURE_FILE);

				RYinDaoQi.MODEL.render(0, 0.0625F);
			}

			GL11.glPopMatrix();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess iBlockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ID;
	}

}
