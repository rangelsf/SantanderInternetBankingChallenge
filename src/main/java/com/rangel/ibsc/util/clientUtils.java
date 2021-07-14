package com.rangel.ibsc.util;

import java.math.BigDecimal;

public class clientUtils {

    //pega um double com possivel mais de 2 casas decimais e transforma em duas casas, arredondando corretamente
    public static BigDecimal truncateDecimal(double x, int numberofDecimals)
    {
        if ( x > 0) {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
        } else {
            return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
        }
    }
}
