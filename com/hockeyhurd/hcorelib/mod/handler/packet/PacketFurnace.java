package com.hockeyhurd.hcorelib.mod.handler.packet;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.mod.tileentity.TileFurnace;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Packet class for TileFurnace.
 *
 * @author hockeyhurd
 * @version 3/15/17
 */
public class PacketFurnace implements IMessage, IMessageHandler<PacketFurnace, IMessage> {

    private final TileFurnace tileFurnace;
    private Vector3<Integer> pos;
    private int[] container;

    @Deprecated
    public PacketFurnace() {
        tileFurnace = null;
    }

    public PacketFurnace(TileFurnace tileFurnace) {
        this.tileFurnace = tileFurnace;
        pos = tileFurnace.worldVec();
        container = new int[tileFurnace.getFieldCount()];

        for (int i = 0; i < container.length; i++)
            container[i] = tileFurnace.getField(i);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        if (pos == null) pos = new Vector3<Integer>(0, 0, 0);

        pos.x = buf.readInt();
        pos.y = buf.readInt();
        pos.z = buf.readInt();

        container = new int[buf.readInt()];

        for (int i = 0; i < container.length; i++)
            container[i] = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (tileFurnace != null && container != null && container.length > 0) {
            buf.writeInt(pos.x);
            buf.writeInt(pos.y);
            buf.writeInt(pos.z);
            buf.writeInt(container.length);

            for (int i = 0; i < container.length; i++)
                buf.writeInt(container[i]);
        }
    }

    @Override
    public IMessage onMessage(PacketFurnace message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT && message.pos != null) {
            final TileFurnace tileFurnace = (TileFurnace) FMLClientHandler.instance().getClient().world.
                    getTileEntity(VectorHelper.toBlockPos((message.pos)));

            if (tileFurnace == null) return null;

            for (int i = 0; i < message.container.length; i++)
                tileFurnace.setField(i, message.container[i]);

        }

        return null;
    }

}
