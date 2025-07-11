package com.zagrski.omni.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConversionsTest {
  @Test
  void testValidConversions() {
    Assertions.assertEquals((byte) 100, Conversions.toByte("100"));
    Assertions.assertEquals((short) 1000, Conversions.toShort("1000"));
    Assertions.assertEquals(10_000, Conversions.toInteger("10000"));
    Assertions.assertEquals(100_000L, Conversions.toLong("100000"));
    Assertions.assertEquals(10.5F, Conversions.toFloat("10.5"));
    Assertions.assertEquals(10.5D, Conversions.toDouble("10.5"));
    Assertions.assertTrue(Conversions.toBoolean("true"));
    Assertions.assertTrue(Conversions.toBoolean("1"));
    Assertions.assertTrue(Conversions.toBoolean("yes"));
    Assertions.assertTrue(Conversions.toBoolean("y"));
    Assertions.assertFalse(Conversions.toBoolean("false"));
    Assertions.assertFalse(Conversions.toBoolean("0"));
    Assertions.assertFalse(Conversions.toBoolean("no"));
    Assertions.assertFalse(Conversions.toBoolean("n"));
  }

  @Test
  void testInvalidConversions() {
    Assertions.assertNull(Conversions.toByte("invalid"));
    Assertions.assertNull(Conversions.toShort("invalid"));
    Assertions.assertNull(Conversions.toInteger("invalid"));
    Assertions.assertNull(Conversions.toLong("invalid"));
    Assertions.assertNull(Conversions.toFloat("invalid"));
    Assertions.assertNull(Conversions.toDouble("invalid"));
    Assertions.assertNull(Conversions.toBoolean("invalid"));
  }

  @Test
  void testConversionsWithDefault() {
    Assertions.assertEquals((byte) 42, Conversions.toByte("invalid", (byte) 42));
    Assertions.assertEquals((short) 42, Conversions.toShort("invalid", (short) 42));
    Assertions.assertEquals(42, Conversions.toInteger("invalid", 42));
    Assertions.assertEquals(42L, Conversions.toLong("invalid", 42L));
    Assertions.assertEquals(42.0F, Conversions.toFloat("invalid", 42.0F));
    Assertions.assertEquals(42.0D, Conversions.toDouble("invalid", 42.0D));
    Assertions.assertTrue(Conversions.toBoolean("invalid", true));
    Assertions.assertFalse(Conversions.toBoolean("invalid", false));
  }
}
