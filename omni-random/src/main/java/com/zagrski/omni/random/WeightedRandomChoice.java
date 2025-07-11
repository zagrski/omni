package com.zagrski.omni.random;

import java.util.Objects;

public class WeightedRandomChoice {
  public static WeightedRandomChoice from(double weight) {
    if (Double.isNaN(weight)) {
      throw new IllegalArgumentException("weight must not be NaN");
    }
    if (Double.isInfinite(weight)) {
      throw new IllegalArgumentException("weight must not be infinite");
    }
    if (weight <= 0.0D) {
      throw new IllegalArgumentException("weight must be greater than 0");
    }
    return new WeightedRandomChoice(weight);
  }

  protected final double weight;

  protected WeightedRandomChoice(double weight) {
    this.weight = weight;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof WeightedRandomChoice that)) {
      return false;
    }
    return Double.compare(weight, that.weight) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(weight);
  }

  @Override
  public String toString() {
    return "WeightedRandomChoice(" + "weight=" + weight + ")";
  }
}
