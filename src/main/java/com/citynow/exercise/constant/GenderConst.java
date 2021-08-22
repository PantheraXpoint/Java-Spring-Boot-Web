package com.citynow.exercise.constant;

import java.util.Map;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
public final class GenderConst {

  private GenderConst() {
    // no args construct
  }

  public static final Map<Integer, String> ID_BY_Gender =
      Map.of(0, "male",
          1, "female",
          2, "other");

  public static boolean notContain(Integer gender) {
    return gender == null || !ID_BY_Gender.keySet().contains(gender);
  }

}
