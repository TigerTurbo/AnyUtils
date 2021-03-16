package com.yshuoo.anyutils.gzip;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP压缩解压缩
 * 2016/8/18.10:44
 */
public class GzipCompressionProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(GzipCompressionProvider.class);
    public byte[] compress(byte[] data) {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             GZIPOutputStream out = new GZIPOutputStream(bytes)) {
            out.write(data);
            out.finish();
            return bytes.toByteArray();
        } catch (IOException e) {
            LOGGER.error("GZIP Compression exception", e);
        }
        return null;
    }

    public byte[] compressString(String data) {
        return compress(data.getBytes(Charset.forName("UTF-8")));
    }
}

