package com.mcxiaoke.packer.cli;

import com.android.apksig.ApkVerifier;
import com.android.apksig.ApkVerifier.Builder;
import com.android.apksig.ApkVerifier.Result;
import com.android.apksig.apk.ApkFormatException;
import com.mcxiaoke.packer.common.PackerCommon;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * User: mcxiaoke
 * Date: 2017/5/26
 * Time: 16:21
 */
public class Bridge {

    public static void writeChannel(File file, String channel) throws IOException {
        PackerCommon.writeChannel(file, channel);
    }

    public static String readChannel(File file) throws IOException {
		String channelFromApk = PackerCommon.readChannel(file);
		System.out.println("> [INFO] moasm.log channelFromApk = " + channelFromApk);
        return channelFromApk;
    }

    public static boolean verifyChannel(File file, String channel) throws IOException {
        String channelFromApk = readChannel(file);
        return (channel.equals(channelFromApk)) && verifyApk(file);
    }

    public static boolean verifyApk(File file) throws IOException {
        System.out.println("> [WARNING] 因谷歌 'com.android.tools.build:apksig:3.1.2' 当前最近版本都还未适配v3签名,故只能跳过签名校验");
        return true;
        
//        ApkVerifier verifier = new Builder(file).build();
//        try {
//            String channelFromApk = readChannel(file);
//            System.out.println("> [INFO] channelFromApk = " + channelFromApk);
//
//            Result result = verifier.verify();
//            return result.isVerified()
//                    && result.isVerifiedUsingV1Scheme()
//                    && result.isVerifiedUsingV2Scheme();
//        } catch (ApkFormatException e) {
//            throw new IOException(e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new IOException(e);
//        }

    }

}
