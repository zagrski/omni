package com.zagrski.omni.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoundingBox2DTest {
  @Test
  void testFromFactoryMethods() {
    BoundingBox2D box = BoundingBox2D.from(0.0D, 0.0D, 10.0D, 10.0D);
    Assertions.assertEquals(0.0D, box.getMinX());
    Assertions.assertEquals(0.0D, box.getMinY());
    Assertions.assertEquals(10.0D, box.getMaxX());
    Assertions.assertEquals(10.0D, box.getMaxY());
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.from(5.0D, 0.0D, 0.0D, 10.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.from(0.0D, 10.0D, 10.0D, 5.0D));
  }

  @Test
  void testFromCenterFactoryMethods() {
    BoundingBox2D box = BoundingBox2D.fromCenter(5.0D, 5.0D, 4.0D, 6.0D);
    Assertions.assertEquals(3.0D, box.getMinX());
    Assertions.assertEquals(2.0D, box.getMinY());
    Assertions.assertEquals(7.0D, box.getMaxX());
    Assertions.assertEquals(8.0D, box.getMaxY());
    BoundingBox2D square = BoundingBox2D.fromCenter(0.0D, 0.0D, 2.0D);
    Assertions.assertEquals(-1.0D, square.getMinX());
    Assertions.assertEquals(-1.0D, square.getMinY());
    Assertions.assertEquals(1.0D, square.getMaxX());
    Assertions.assertEquals(1.0D, square.getMaxY());
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.fromCenter(0.0D, 0.0D, -1.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.fromCenter(0.0D, 0.0D, -1.0D, 1.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox2D.fromCenter(0.0D, 0.0D, 1.0D, -1.0D));
  }

  @Test
  void testAccessorsAndDerivedMetrics() {
    BoundingBox2D box = BoundingBox2D.from(2.0D, 3.0D, 8.0D, 7.0D);
    Assertions.assertEquals(6.0D, box.getWidth());
    Assertions.assertEquals(4.0D, box.getHeight());
    Assertions.assertEquals(5.0D, box.getCenterX());
    Assertions.assertEquals(5.0D, box.getCenterY());
    Assertions.assertEquals(24.0D, box.getArea());
    Assertions.assertEquals(20.0D, box.getPerimeter());
  }

  @Test
  void testContainsPointAndBox() {
    BoundingBox2D box = BoundingBox2D.from(0.0D, 0.0D, 10.0D, 10.0D);
    Assertions.assertTrue(box.contains(5.0D, 5.0D));
    Assertions.assertTrue(box.contains(0.0D, 0.0D));
    Assertions.assertFalse(box.contains(10.1D, 5.0D));
    Assertions.assertFalse(box.contains(5.0D, -0.1D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.contains(null));
    BoundingBox2D inside = BoundingBox2D.from(2.0D, 2.0D, 8.0D, 8.0D);
    Assertions.assertTrue(box.contains(inside));
    BoundingBox2D overlap = BoundingBox2D.from(-1.0D, -1.0D, 5.0D, 5.0D);
    Assertions.assertFalse(box.contains(overlap));
  }

  @Test
  void testIntersectionAndUnion() {
    BoundingBox2D a = BoundingBox2D.from(0.0D, 0.0D, 5.0D, 5.0D);
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.union(null));
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.intersection(null));
    BoundingBox2D b = BoundingBox2D.from(3.0D, 3.0D, 6.0D, 6.0D);
    Assertions.assertEquals(BoundingBox2D.from(3.0D, 3.0D, 5.0D, 5.0D), a.intersection(b));
    Assertions.assertEquals(BoundingBox2D.from(0.0D, 0.0D, 6.0D, 6.0D), a.union(b));
    BoundingBox2D c = BoundingBox2D.from(6.0D, 6.0D, 7.0D, 7.0D);
    Assertions.assertNull(a.intersection(c));
  }

  @Test
  void testIntersects() {
    BoundingBox2D a = BoundingBox2D.from(0.0D, 0.0D, 5.0D, 5.0D);
    BoundingBox2D b = BoundingBox2D.from(4.0D, 4.0D, 10.0D, 10.0D);
    Assertions.assertTrue(a.intersects(b));
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.intersects(null));
    BoundingBox2D c = BoundingBox2D.from(6.0D, 6.0D, 8.0D, 8.0D);
    Assertions.assertFalse(a.intersects(c));
  }

  @Test
  void testExpandShrinkTranslate() {
    BoundingBox2D box = BoundingBox2D.from(2.0D, 2.0D, 4.0D, 4.0D);
    Assertions.assertEquals(BoundingBox2D.from(1.0D, 1.0D, 5.0D, 5.0D), box.expand(1.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.expand(-1.0D));
    Assertions.assertEquals(BoundingBox2D.from(3.0D, 3.0D, 3.0D, 3.0D), box.shrink(1.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.shrink(-1.0D));
    Assertions.assertEquals(BoundingBox2D.from(4.0D, 1.0D, 6.0D, 3.0D), box.translate(2.0D, -1.0D));
  }
}
