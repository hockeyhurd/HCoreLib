package com.hockeyhurd.hcorelib.mod.handler.packet;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.mod.tileentity.multiblock.MultiblockController;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author hockeyhurd
 * @version 12/30/2017.
 */
public class PacketController implements IMessage, IMessageHandler<PacketController, IMessage> {

    private final MultiblockController controller;
    private Vector3<Integer> pos;

    @Deprecated
    public PacketController() {
        controller = null;
        pos = new Vector3<>(0, 0, 0);
    }

    public PacketController(MultiblockController controller) {
        this.controller = controller;
        this.pos = controller.worldVec();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (pos == null)
            pos = new Vector3<>(0, 0, 0);

        pos.x = buf.readInt();
        pos.y = buf.readInt();
        pos.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (controller != null) {
            buf.writeInt(pos.x);
            buf.writeInt(pos.y);
            buf.writeInt(pos.z);
        }
    }

    @Override
    public IMessage onMessage(PacketController message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT && message.pos != null) {
            final MultiblockController controller = (MultiblockController) FMLClientHandler.instance().getClient().world.getTileEntity(
                    VectorHelper.toBlockPos(message.pos));

            if (controller != null) {
                controller.checkIsCompleteMultiblock();
                controller.notifyChildren();
                controller.updateState(controller.getMultiblockState());
            }
        }

        return null;
    }
}
