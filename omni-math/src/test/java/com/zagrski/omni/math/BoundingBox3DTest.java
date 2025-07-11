package com.zagrski.omni.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoundingBox3DTest {
  @Test
  void testFromFactoryMethods() {
    BoundingBox3D box = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D);
    Assertions.assertEquals(0.0D, box.getMinX());
    Assertions.assertEquals(0.0D, box.getMinY());
    Assertions.assertEquals(0.0D, box.getMinZ());
    Assertions.assertEquals(10.0D, box.getMaxX());
    Assertions.assertEquals(10.0D, box.getMaxY());
    Assertions.assertEquals(10.0D, box.getMaxZ());
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(5.0D, 0.0D, 0.0D, 0.0D, 10.0D, 10.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(0.0D, 10.0D, 0.0D, 10.0D, 5.0D, 10.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.from(0.0D, 0.0D, 10.0D, 10.0D, 10.0D, 5.0D));
  }

  @Test
  void testFromCenterFactoryMethods() {
    BoundingBox3D box = BoundingBox3D.fromCenter(5.0D, 5.0D, 5.0D, 4.0D, 6.0D, 8.0D);
    Assertions.assertEquals(3.0D, box.getMinX());
    Assertions.assertEquals(2.0D, box.getMinY());
    Assertions.assertEquals(1.0D, box.getMinZ());
    Assertions.assertEquals(7.0D, box.getMaxX());
    Assertions.assertEquals(8.0D, box.getMaxY());
    Assertions.assertEquals(9.0D, box.getMaxZ());
    BoundingBox3D cube = BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 2.0D);
    Assertions.assertEquals(-1.0D, cube.getMinX());
    Assertions.assertEquals(-1.0D, cube.getMinY());
    Assertions.assertEquals(-1.0D, cube.getMinZ());
    Assertions.assertEquals(1.0D, cube.getMaxX());
    Assertions.assertEquals(1.0D, cube.getMaxY());
    Assertions.assertEquals(1.0D, cube.getMaxZ());
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, -1.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, -1.0D, 1.0D, 1.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 1.0D, -1.0D, 1.0D));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> BoundingBox3D.fromCenter(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, -1.0D));
  }

  @Test
  void testAccessorsAndDerivedMetrics() {
    BoundingBox3D box = BoundingBox3D.from(1.0D, 2.0D, 3.0D, 5.0D, 6.0D, 7.0D);
    Assertions.assertEquals(4.0D, box.getWidth());
    Assertions.assertEquals(4.0D, box.getHeight());
    Assertions.assertEquals(4.0D, box.getDepth());
    Assertions.assertEquals(3.0D, box.getCenterX());
    Assertions.assertEquals(4.0D, box.getCenterY());
    Assertions.assertEquals(5.0D, box.getCenterZ());
    Assertions.assertEquals(64.0D, box.getVolume());
    Assertions.assertEquals(96.0D, box.getSurfaceArea());
  }

  @Test
  void testContainsPointAndBox() {
    BoundingBox3D box = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D);
    Assertions.assertTrue(box.contains(5.0D, 5.0D, 5.0D));
    Assertions.assertTrue(box.contains(0.0D, 0.0D, 0.0D));
    Assertions.assertFalse(box.contains(11.0D, 5.0D, 5.0D));
    Assertions.assertFalse(box.contains(5.0D, -1.0D, 5.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.contains(null));
    BoundingBox3D inside = BoundingBox3D.from(2.0D, 2.0D, 2.0D, 8.0D, 8.0D, 8.0D);
    Assertions.assertTrue(box.contains(inside));
    BoundingBox3D overlap = BoundingBox3D.from(-1.0D, -1.0D, -1.0D, 5.0D, 5.0D, 5.0D);
    Assertions.assertFalse(box.contains(overlap));
  }

  @Test
  void testIntersectionAndUnion() {
    BoundingBox3D a = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 5.0D, 5.0D, 5.0D);
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.union(null));
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.intersection(null));
    BoundingBox3D b = BoundingBox3D.from(3.0D, 3.0D, 3.0D, 6.0D, 6.0D, 6.0D);
    Assertions.assertEquals(BoundingBox3D.from(3.0D, 3.0D, 3.0D, 5.0D, 5.0D, 5.0D),
        a.intersection(b));
    Assertions.assertEquals(BoundingBox3D.from(0.0D, 0.0D, 0.0D, 6.0D, 6.0D, 6.0D), a.union(b));
    BoundingBox3D c = BoundingBox3D.from(6.1D, 6.1D, 6.1D, 7.0D, 7.0D, 7.0D);
    Assertions.assertNull(a.intersection(c));
  }

  @Test
  void testIntersects() {
    BoundingBox3D a = BoundingBox3D.from(0.0D, 0.0D, 0.0D, 5.0D, 5.0D, 5.0D);
    BoundingBox3D b = BoundingBox3D.from(4.0D, 4.0D, 4.0D, 10.0D, 10.0D, 10.0D);
    Assertions.assertTrue(a.intersects(b));
    Assertions.assertThrows(IllegalArgumentException.class, () -> a.intersects(null));
    BoundingBox3D c = BoundingBox3D.from(6.0D, 6.0D, 6.0D, 8.0D, 8.0D, 8.0D);
    Assertions.assertFalse(a.intersects(c));
  }

  @Test
  void testExpandShrinkTranslate() {
    BoundingBox3D box = BoundingBox3D.from(1.0D, 1.0D, 1.0D, 3.0D, 3.0D, 3.0D);
    Assertions.assertEquals(BoundingBox3D.from(0.0D, 0.0D, 0.0D, 4.0D, 4.0D, 4.0D),
        box.expand(1.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.expand(-1.0D));
    Assertions.assertEquals(BoundingBox3D.from(2.0D, 2.0D, 2.0D, 2.0D, 2.0D, 2.0D),
        box.shrink(1.0D));
    Assertions.assertThrows(IllegalArgumentException.class, () -> box.shrink(-1.0D));
    Assertions.assertEquals(BoundingBox3D.from(3.0D, 0.0D, 2.0D, 5.0D, 2.0D, 4.0D),
        box.translate(2.0D, -1.0D, 1.0D));
  }
}
