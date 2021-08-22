package com.citynow.exercise.constant;

import java.util.Map;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
public final class TypeConst {

  private TypeConst() {
    // no args construct
  }

  public static final Map<Integer, String> ID_BY_TYPE =
      Map.of(0, "manage",
          1, "employee",
          2, "guest");

  public static boolean notContain(Integer type) {
    return type == null || !ID_BY_TYPE.keySet().contains(type);
  }

}
