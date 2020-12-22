package com.mcxiaoke.packer.support.walleV3;

import com.meituan.android.walle.PayloadReader;
import com.meituan.android.walle.PayloadWriter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * bridge class between support and common
 * User: mcxiaoke
 * Date: 2017/6/13
 * Time: 14:06
 */

public class SupportV3 {

    public static ByteBuffer readBlock(final File apkFile, final int id)
            throws IOException {
        byte[] bytes = PayloadReader.get(apkFile, id);
        @SuppressWarnings("UnnecessaryLocalVariable")
        ByteBuffer ret = null == bytes ? null : ByteBuffer.wrap(bytes);
        if (null != ret) {
            // 注意: 美团的walle库底层是'小端模式', 而这个ByteBuffer默认是大端模式, 在解析int值等时候会有问题, 故这里转换
            ret.order(ByteOrder.LITTLE_ENDIAN);
        }
        return ret;
    }

    public static byte[] readBytes(final File apkFile, final int id)
            throws IOException {
        return PayloadReader.get(apkFile, id);
    }

    public static void writeBlock(final File apkFile, final int id,
                                  final ByteBuffer buffer) throws IOException {
        try {
            PayloadWriter.put(apkFile, id, buffer, false);
        } catch (Exception e) {
            System.out.println("> [Error] SupportV3.writeBlock E:" + e);
            e.printStackTrace();
        }
    }

    public static void writeBlock(final File apkFile, final int id,
                                  final byte[] bytes) throws IOException {
        writeBlock(apkFile, id, ByteBuffer.wrap(bytes));
    }
}
