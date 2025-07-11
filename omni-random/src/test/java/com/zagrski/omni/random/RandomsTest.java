package com.zagrski.omni.random;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomsTest {
  final Random fixedRandom = new Random(38L);

  @Test
  void testRandomByte() {
    Assertions.assertEquals((byte) 9, Randoms.randomByte(fixedRandom, (byte) 1, (byte) 10));
    Assertions.assertEquals((byte) 7, Randoms.randomByte(fixedRandom, (byte) 7, (byte) 7));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomByte(null, (byte) 1, (byte) 2));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomByte(fixedRandom, (byte) 5, (byte) 4));
  }

  @Test
  void testRandomShort() {
    Assertions.assertEquals((short) 9, Randoms.randomShort(fixedRandom, (short) 1, (short) 10));
    Assertions.assertEquals((short) 7, Randoms.randomShort(fixedRandom, (short) 7, (short) 7));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomShort(null, (short) 1, (short) 2));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomShort(fixedRandom, (short) 10, (short) 5));
  }

  @Test
  void testRandomInteger() {
    Assertions.assertEquals(9, Randoms.randomInteger(fixedRandom, 1, 10));
    Assertions.assertEquals(7, Randoms.randomInteger(fixedRandom, 7, 7));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomInteger(null, 1, 2));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomInteger(fixedRandom, 5, 4));
  }

  @Test
  void testRandomLong() {
    Assertions.assertEquals(6L, Randoms.randomLong(fixedRandom, 1L, 10L));
    Assertions.assertEquals(8L, Randoms.randomLong(fixedRandom, 8, 8));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.randomLong(null, 1, 2));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomLong(fixedRandom, 10, 5));
  }

  @Test
  void testRandomFloat() {
    Assertions.assertEquals(1.7279222F, Randoms.randomFloat(fixedRandom, 1.0F, 2.0F), 1.0E-6F);
    Assertions.assertEquals(3.0f, Randoms.randomFloat(fixedRandom, 3.0F, 3.0F));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomFloat(null, 1.0F, 2.0F));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomFloat(fixedRandom, 5.0F, 4.0F));
  }

  @Test
  void testRandomDouble() {
    Assertions.assertEquals(1.7279220220228495D, Randoms.randomDouble(fixedRandom, 1.0F, 2.0F),
        1.0E-9D);
    Assertions.assertEquals(5.0D, Randoms.randomDouble(fixedRandom, 5.0D, 5.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomDouble(null, 1.0D, 2.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomDouble(fixedRandom, 4.0D, 3.0));
  }

  @Test
  void testRandomBoolean() {
    Assertions.assertTrue(Randoms.randomBoolean(fixedRandom));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.randomBoolean(null));
  }

  @Test
  void testRandomElementArray() {
    String[] array = {"a", "b", "c"};
    Assertions.assertEquals("a", Randoms.randomElement(fixedRandom, array));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(null, array));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (String[]) null));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, new String[0]));
    Assertions.assertEquals("x", Randoms.randomElement(fixedRandom, new String[] {"x"}));
  }

  @Test
  void testRandomElementList() {
    List<Integer> list = Arrays.asList(10, 20, 30);
    Assertions.assertEquals(10, Randoms.randomElement(fixedRandom, list));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(null, list));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (List<Integer>) null));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, List.of()));
    Assertions.assertEquals(99, Randoms.randomElement(fixedRandom, List.of(99)));
  }

  @Test
  void testRandomElementCollection() {
    Collection<String> collection = new LinkedHashSet<>(Arrays.asList("x", "y", "z"));
    Assertions.assertEquals("x", Randoms.randomElement(fixedRandom, collection));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(null, collection));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, (Collection<String>) null));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomElement(fixedRandom, Set.of()));
    Assertions.assertEquals("only", Randoms.randomElement(fixedRandom, Set.of("only")));
  }

  @Test
  void testRandomWeightedChoice() {
    List<WeightedRandomChoice> choices = Arrays.asList(WeightedRandomChoice.from(1.0D),
        WeightedRandomChoice.from(2.0D), WeightedRandomChoice.from(3.0D));
    WeightedRandomChoice choice = Randoms.randomWeightedChoice(fixedRandom, choices);
    Assertions.assertEquals(3.0D, choice.weight);
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomWeightedChoice(null, choices));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomWeightedChoice(fixedRandom, (List<WeightedRandomChoice>) null));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.randomWeightedChoice(fixedRandom, List.of()));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms
        .randomWeightedChoice(fixedRandom, Arrays.asList(WeightedRandomChoice.from(1.0D), null)));
  }

  @Test
  void testTotalWeight() {
    List<WeightedRandomChoice> list =
        Arrays.asList(WeightedRandomChoice.from(2.5D), WeightedRandomChoice.from(3.5D));
    Assertions.assertEquals(6.0D, Randoms.totalWeight(list));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.totalWeight(null));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Randoms.totalWeight(Arrays.asList(WeightedRandomChoice.from(1.0D), null)));
  }

  @Test
  void testChance1() {
    Assertions.assertTrue(Randoms.chance1(fixedRandom, 1.0D));
    Assertions.assertFalse(Randoms.chance1(fixedRandom, 0.0D));
    Assertions.assertFalse(Randoms.chance1(fixedRandom, 0.5D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.chance1(null, 0.5D));
  }

  @Test
  void testRandomChance1() {
    Assertions.assertEquals(0.7279220220228493D, Randoms.randomChance1(fixedRandom), 1.0E-9D);
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.randomChance1(null));
  }

  @Test
  void testChance100() {
    Assertions.assertTrue(Randoms.chance100(fixedRandom, 100.0D));
    Assertions.assertFalse(Randoms.chance100(fixedRandom, 0.0D));
    Assertions.assertFalse(Randoms.chance100(fixedRandom, 50.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> Randoms.chance100(null, 50.0D));
  }
}
