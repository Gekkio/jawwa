package fi.jawsy.jawwa.lang.codec;

import java.util.Arrays;

import lombok.val;

import com.google.common.base.Preconditions;

public final class Base32Crockford {

    private Base32Crockford() {
    }

    private static final char[] ENCODE_TABLE = "0123456789abcdefghjkmnpqrstvwxyz".toCharArray();

    private static final int[] DECODE_TABLE;

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    static {
        DECODE_TABLE = new int[123];
        Arrays.fill(DECODE_TABLE, -1);

        for (int i = 0; i < ENCODE_TABLE.length; i++) {
            DECODE_TABLE[ENCODE_TABLE[i]] = i;
            DECODE_TABLE[Character.toUpperCase(ENCODE_TABLE[i])] = i;
        }
        DECODE_TABLE['O'] = 0;
        DECODE_TABLE['o'] = 0;
        DECODE_TABLE['I'] = 1;
        DECODE_TABLE['i'] = 1;
        DECODE_TABLE['L'] = 1;
        DECODE_TABLE['l'] = 1;
    }

    public static String encode(byte[] data) {
        if (data.length == 0)
            return "";

        val sb = new StringBuilder(data.length * 8 / 5);
        val totalBits = ((int) Math.ceil(data.length * 8.0 / 5)) * 5;

        int currentBit = 0;
        int accum = 0;

        while (currentBit < totalBits) {
            val index = currentBit / 8;
            int source = (index < data.length) ? (data[index] & 0xFF) : 0;
            val maxSourceBit = (index + 1) * 8;

            val bitsAvailable = maxSourceBit - currentBit;
            if (bitsAvailable < 5) {
                val sourceShift = 5 - bitsAvailable;
                accum = (source << sourceShift) & 0x1f;

                currentBit += bitsAvailable;
            } else {
                val sourceOffset = currentBit % 8;
                val targetOffset = currentBit % 5;

                val bitsNeeded = 5 - targetOffset;
                val sourceShift = 8 - bitsNeeded - sourceOffset;
                val value = accum | ((source >> sourceShift) & 0x1f);

                sb.append(ENCODE_TABLE[value]);

                currentBit += bitsNeeded;
                accum = 0;
            }
        }

        return sb.toString();
    }

    public static byte[] decode(String data) {
        if (data.isEmpty())
            return EMPTY_BYTE_ARRAY;

        val result = new byte[data.length() * 5 / 8];
        val totalBits = ((int) Math.floor(data.length() * 5.0 / 8)) * 8;

        int currentBit = 0;
        int accum = 0;

        while (currentBit < totalBits) {
            val index = currentBit / 5;
            val ch = data.charAt(index);

            Preconditions.checkElementIndex(ch, DECODE_TABLE.length, "Invalid character '" + ch + "'");
            val source = DECODE_TABLE[ch];
            Preconditions.checkState(source != -1, "Invalid character '" + ch + "'");

            val targetOffset = currentBit % 8;
            val bitsNeeded = 8 - targetOffset;

            if (bitsNeeded <= 5) {
                val sourceShift = 5 - bitsNeeded;
                val value = accum | ((source >> sourceShift) & 0x1f);

                result[currentBit / 8] = (byte) value;

                currentBit += bitsNeeded;
                accum = 0;
            } else {
                val sourceOffset = currentBit % 5;
                val bitsAvailable = 5 - sourceOffset;

                val sourceShift = 8 - bitsAvailable - targetOffset;
                accum = accum | ((source << sourceShift) & 0xff);

                currentBit += bitsAvailable;
            }
        }
        return result;
    }

    public static int decodeInt(String data) {
        val bytes = decode(data);
        int result = 0;
        for (int i = 0; i < bytes.length; i++) {
            val b = bytes[i] & 0xFF;
            result |= (b << ((bytes.length - i - 1) * 8));
        }
        return result;
    }

    public static long decodeLong(String data) {
        val bytes = decode(data);
        long result = 0;
        for (int i = 0; i < bytes.length; i++) {
            val b = bytes[i] & 0xFFL;
            result |= (b << ((bytes.length - i - 1) * 8));
        }
        return result;
    }

    public static String encodeInt(int value) {
        val significantBits = (Integer.SIZE - Integer.numberOfLeadingZeros(value));
        val significantBytes = (int) Math.ceil(significantBits / 8.0);
        val bytes = new byte[significantBytes];
        for (int i = (significantBytes - 1); i >= 0; i--) {
            bytes[significantBytes - i - 1] = (byte) (value >> (i * 8));
        }
        return encode(bytes);
    }

    public static String encodeLong(long value) {
        val significantBits = (Long.SIZE - Long.numberOfLeadingZeros(value));
        val significantBytes = (int) Math.ceil(significantBits / 8.0);
        val bytes = new byte[significantBytes];
        for (int i = (significantBytes - 1); i >= 0; i--) {
            bytes[significantBytes - i - 1] = (byte) (value >> (i * 8));
        }
        return encode(bytes);
    }

}
